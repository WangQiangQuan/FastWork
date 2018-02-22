package fast.wq.com.fastandroid.download;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import fast.wq.com.fastandroid.utils.IntentUtils;

/**
 * Created by wangqiang on 2018/2/18.
 */

public class DownLoadService extends Service {
    private static final String TAG = "DownLoadService";

    public static final String DOWNLOAD_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/downloads/";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_STOP = "ACTION_STOP";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";

    public static final String fileInof = "fileInof";

    public static final int Mes_init = 1;

    private DownLoadTask mTask;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获得传过来的参数。
        if (ACTION_START.equals(intent.getAction())) {
            FileInfo mFileInfo = (FileInfo) IntentUtils.getParcelable(intent, fileInof);
            Log.i(TAG, "start: " + mFileInfo.toString());
            new InitThread(mFileInfo).start();
        } else if (ACTION_STOP.equals(intent.getAction())) {
            FileInfo mFileInfo = (FileInfo) IntentUtils.getParcelable(intent, fileInof);
            Log.i(TAG, "stop: " + mFileInfo.toString());
            if (mTask != null){
                mTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


   public  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Mes_init:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    Log.i(TAG, "handleMessage: ");
                    mTask = new DownLoadTask(DownLoadService.this,fileInfo);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 初始化子线程
     */
    class InitThread extends Thread {
        FileInfo mFileInfo;

        public InitThread(FileInfo mFileInfo) {
            this.mFileInfo = mFileInfo;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            try {
                //连接网络文件
                URL url = new URL(mFileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                int length = -1;
                if (conn.getResponseCode() == 200) {
                    length = conn.getContentLength();
                }
                if (length <= 0) {
                    return;
                }
                //获得文件长度
                //本地创建文件

                File dir = new File(DOWNLOAD_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = new File(dir, mFileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                //设置文件长度
                raf.setLength(length);

                mFileInfo.setLength(length);
                mHandler.obtainMessage(Mes_init, mFileInfo).sendToTarget();
                super.run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
