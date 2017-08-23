package fast.wq.com.fastandroid.imageloader;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fast.wq.com.fastandroid.cache.ImageCache;
import fast.wq.com.fastandroid.cache.ImageMemoryCache;
import fast.wq.com.fastandroid.utils.BitmapUtils;

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

    private void init(Type type) {
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                mPoolThreadHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        //线程池去取出一个任务
                        mExecutorService.execute(getTask());
                    }


                };
                Looper.loop();
            }
        };
        mPoolThread.start();

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
    private Handler mUIHandler;

    //
    public void displayImage(final ImageView mImageView, final String path) {

        mImageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView iamgeview = holder.imageView;
                    String path = holder.path;
                    if (mImageView.getTag().toString().equals(path)) {
                        mImageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap mBitmap = imageCache.get(path);
        if (mBitmap != null) {
            Message message = Message.obtain();
            ImageBeanHolder imageBeanHolder = new ImageBeanHolder();
            imageBeanHolder.bitmap = mBitmap;
            imageBeanHolder.path = path;
            imageBeanHolder.imageView = mImageView;
            message.obj = imageBeanHolder;
            mUIHandler.sendMessage(message);
        } else {
            addTask(new Runnable() {

                @Override
                public void run() {
                    //加载图片
                    ImageSize imageSize= getImageViewSize(mImageView);
                   Bitmap mBitmap =  BitmapUtils.decodeSampleBitmapFromPath(path,imageSize.width,imageSize.height);
                }
            });
        }
    }

    private void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    private Runnable getTask() {
        if (mCurrentType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mCurrentType == Type.LIFO) {
            return mTaskQueue.removeLast();
        }
        return null;
    }

    @SuppressLint("NewApi")
    protected ImageSize getImageViewSize(ImageView mImageView) {
        ImageSize mImageSize = new ImageSize();

        DisplayMetrics displayMetr = mImageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams lp = mImageView.getLayoutParams();
        int width = mImageView.getWidth();//获取实际宽度
        if (width <= 0) {
            width = lp.width;// 获取layout 声明的宽度
        }
        if (width <= 0 ){
            width = mImageView.getMaxWidth();//检查最大值
        }
        if (width <= 0 ){
            width = displayMetr.widthPixels;//屏幕宽度
        }
        return mImageSize;
    }

    private class ImageBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }

    private class ImageSize {
        int width;
        int height;
    }
}
