package fast.wq.com.fastandroid.view.ImageLoader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**

 */

public class ImageLoader {
    /**
     * 单利
     */
    public static ImageLoader mInstance;

    private ImageLoader() {
    }

    public static ImageLoader getmInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }
    /**
     * 成员变量
     */
    private LruCache mLruCache;
    private ExecutorService mThreadPool;
    private static final int Def = 1;

    /**
     * 队列调度方式
     */
    private Type mType = Type.LIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskQueue;
    public enum Type{
        FIFI,LIFO
    }

    /**
     * 后台轮询线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;

    private Handler  mUiHanlder;


    private void init(){
        mPoolThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
            }
        });
        mPoolThread.start();


        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;

        mLruCache = new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        mThreadPool = Executors.newFixedThreadPool(Def);
        mTaskQueue  = new LinkedList<>();

    }


    public void loadImage(String path,ImageView imageView){

        imageView.setTag(path);

        if (mUiHanlder == null){
            mUiHanlder = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                   //获取图片，回调图片显示
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap m = holder.m;
                    ImageView img = holder.img;
                    String path = holder.path;

                    //ImageView 会复用
                    if (path.equals(img.getTag().toString())){
                        img.setImageBitmap(m);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null){
            Message m = Message.obtain();
            ImageBeanHolder holder = new ImageBeanHolder();
            holder.img = imageView;
            holder.path = path;
            holder.m = bm;
            m.obj = holder;
            mUiHanlder.sendMessage(m);

        }else {
            addTask(new Runnable(){
                @Override
                public void run() {
                    
                }
            });
        }
    }

    private void addTask(Runnable runnable) {

    }

    private Bitmap getBitmapFromLruCache(String path) {
        return (Bitmap) mLruCache.get(path);
    }

    private class ImageBeanHolder{
        Bitmap m;
        ImageView img;
        String path;
    }

}
