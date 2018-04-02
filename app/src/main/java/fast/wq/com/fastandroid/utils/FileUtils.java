package fast.wq.com.fastandroid.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import fast.wq.com.fastandroid.bean.SerializableBean;

/**
 * Created by admin on 2018/2/26.
 */

public class FileUtils {

    /**
     * 普通的文件复制方法
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     * @throws FileNotFoundException 未找到文件异常
     */
    public void fileCopyNormal(File fromFile, File toFile) throws FileNotFoundException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(fromFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(toFile));
            byte[] bytes = new byte[1024];
            int i;
            //读取到输入流数据，然后写入到输出流中去，实现复制
            while ((i = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用filechannel进行文件复制
     *
     * @param fromFile 源文件
     * @param toFile   目标文件
     */
    public void fileCopyWithFileChannel(File fromFile, File toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 序列化本地
     */
    static String path = "/AAA/";
    static String name = "p.txt";
    public static void putObj(){
        SerializableBean bean = new SerializableBean();
        bean.name = "序列化本地";

        try {
//            File f = new File("d:" + File.separator+"putObj.txt");

            File f  = SDcardUtils.getExternalStorageDirectory();
            File dir = new File(f.getCanonicalPath() + path);

            if (!dir.exists()){
                dir.mkdirs();
            }
            File targetFile = new File(f.getCanonicalPath() + path+name);
            Log.i("wang", "putObj: "+targetFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(targetFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bean);

            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getObj(){
        FileInputStream fis = null;
        try {
//            fis = new FileInputStream("putObj.out");
            File f  = SDcardUtils.getExternalStorageDirectory();
            File dir = new File(f.getCanonicalPath() + path);

            if (!dir.exists()){
                dir.mkdirs();
            }
            File targetFile = new File(f.getCanonicalPath() + path+name);
            fis = new FileInputStream(targetFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            SerializableBean bean = (SerializableBean) ois.readObject();
            Log.i("wang", "getObj: "+bean.name);
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
