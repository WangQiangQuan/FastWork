package fast.wq.com.fastandroid.download;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 下载任务类
 */

public class DownLoadTask {

    private Context mContext;
    private FileInfo mFileInfo;
    private ThreadDao mDao;
    private int finished;
    public boolean isPause = false;

    public DownLoadTask(Context mContext, FileInfo mFileInfo) {
        this.mContext = mContext;
        this.mFileInfo = mFileInfo;
        mDao = new ThreadDaoImpl(mContext);
    }

    public void download(){
        List<ThreadInfo> threadinfo=  mDao.getThreads(mFileInfo.getUrl());
        ThreadInfo mThreadInfo = null;
        if (threadinfo.size() == 0){
            mThreadInfo = new ThreadInfo(0,mFileInfo.getUrl(),0,mFileInfo.getLength(),0);
        }else {
            mThreadInfo = threadinfo.get(0);
        }

        //创建子线程下载
        new DownLoadThread(mThreadInfo).start();
    }
    class DownLoadThread extends Thread {
        private ThreadInfo mThreadInfo = null;

        public DownLoadThread(ThreadInfo mThreadInfo) {
            this.mThreadInfo = mThreadInfo;
        }

        @Override
        public void run() {
            //向数据库插入线程信息
            if (!mDao.isExiste(mThreadInfo.getUrl(), mThreadInfo.getId())) {
                mDao.insertThread(mThreadInfo);
            }
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            InputStream in = null;
            try {
                // 设置下载位置
                URL url = new URL(mThreadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");

                int start = mThreadInfo.getStart() + mThreadInfo.getFinished();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + mThreadInfo.getEnd());
                //设置文件写入位置
                File file = new File(DownLoadService.DOWNLOAD_PATH, mFileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                //开始下载
                finished += mThreadInfo.getFinished();
                Intent mIntent = new Intent(DownLoadService.ACTION_UPDATE);
                if (conn.getResponseCode() == 200 || conn.getResponseCode() == 206) {
                     in = conn.getInputStream();
                    byte[] buffer = new byte[1024*4];
                    int len = -1;
                    while ((len = in.read(buffer) )!= -1){
                        raf.write(buffer,0,len);
                        //把下载进度广播通知activity
                        finished += len;
                        mIntent.putExtra("finished",finished *100 /mFileInfo.getLength());
                        mContext.sendBroadcast(mIntent);

                        //如果下载暂停，保存下载进度
                        if (isPause){
                            mDao.updateThread(mThreadInfo.getUrl(),mThreadInfo.getId(),mThreadInfo.getFinished());
                        }
                    }

                    //下载完成，删除信息
                    mDao.deleteThread(mThreadInfo.getUrl(),mThreadInfo.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                conn.disconnect();
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
