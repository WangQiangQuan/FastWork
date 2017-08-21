package fast.wq.com.fastandroid.imageloader;

import android.os.Handler;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fast.wq.com.fastandroid.cache.ImageCache;
import fast.wq.com.fastandroid.cache.ImageMemoryCache;

/**
 * 当第一次加载ImageLoader 时并不会初始化sInstance
 * 由于内部静态类只会被加载一次，故该实现方式时线程安全的
 */

public class ImageLoader {

    private ImageLoader() {

//        init( type);
    }

    public static ImageLoader getInstance() {
        return SingletonHolder.sInstance;
    }

    //在第一次被引用时被加载
    public static class SingletonHolder {
        private static final ImageLoader sInstance = new ImageLoader();
    }

    private void init(Type type){
        mPoolThread = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };

        mTaskQueue = new LinkedList<>();
    }



    ImageCache imageCache = new ImageMemoryCache();

    //注入具体的实现类
    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    /**
     * 线程池
     */
    ExecutorService mExecutorService
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //任务队列
    private Type mCurrentType = Type.LIFO;

    public enum Type {
        FIFO, LIFO;
    }

    private LinkedList<Runnable> mTaskQueue;

    //后台轮询线程
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;
    /**
     * ui中 handler
     */
    private Handler mHandler;
}
