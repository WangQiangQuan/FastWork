package fast.wq.com.fastandroid.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;

/**
 * https://www.cnblogs.com/yangykaifa/p/6832196.html
 * 2、StatFs 类：
 * StatFs 一个模拟linux的df命令的一个类,获得SD卡和手机内存的使用情况
 */

public class SDcardUtils {
    static final int ERROR = -1;

    public static void test() {

        Log.i("wang", "test: 外部存储是否可用 externalMemoryAvailable= " + externalMemoryAvailable());
        Log.i("wang", "test: 获取手机内部可用空间大小 getAvailableInternalMemorySize= " + formatSize(getAvailableInternalMemorySize()));
        Log.i("wang", "test: 获取手机内部空间大小 getTotalInternalMemorySize= " + formatSize(getTotalInternalMemorySize()));
        Log.i("wang", "test: 获取手机外部可用空间大小 getAvailableExternalMemorySize= " + formatSize(getAvailableExternalMemorySize()));
        Log.i("wang", "test: 获取手机外部空间大小 getTotalExternalMemorySize()= " + formatSize(getTotalExternalMemorySize()));
    }

    /**
     * 外部存储是否可用
     *
     * @return
     */
    static public boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取手机内部可用空间大小
     *
     * @return
     */
    static public long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }


    /**
     * 获取手机内部空间大小
     *
     * @return
     */
    static public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }


    /**
     * 获取手机外部可用空间大小
     *
     * @return
     */
    static public long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }


    /**
     * 获取手机外部空间大小
     *
     * @return
     */
    static public long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }


    static public String formatSize(long size) {
        String suffix = null;


        if (size >= 1024) {
            suffix = "KiB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MiB";
                size /= 1024;
            }
        }


        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));
        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }


        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    // /storage/emulated/0
    public static File getExternalStorageDirectory() {
        File parentPath = null;
        //判断实际是否有SD卡，且应用程序是否有读写SD卡的能力，有则返回true
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
             parentPath = Environment.getExternalStorageDirectory();
        }

        return parentPath;
    }

    /**
     * Copy from volley
     *
     * @param context
     * @param uniqueName  wqq
     * @return /storage/emulated/0/Android/data/fast.wq.com.fastandroid/cache/wqq
     *
     *
     * getCacheDir()方法用于获取/data/data/<application package>/cache目录
    getFilesDir()方法用于获取/data/data/<application package>/files目录

    可以看到，当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路径，否则就调用getCacheDir()方法来获取缓存路径。前者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，而后者获取到的是 /data/data/<application package>/cache
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath = ("mounted".equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable()) && getExternalCacheDir(context) != null?getExternalCacheDir(context).getPath():context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }


    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        return Environment.isExternalStorageRemovable();
    }

    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }
    //--------end
}
