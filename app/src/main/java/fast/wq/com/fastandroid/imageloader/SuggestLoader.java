package fast.wq.com.fastandroid.imageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangqiang on 2019/8/9.
 * 1 尽可能的避免内存溢出
 * a lruCache
 * 2 ui充分流畅。
 * 异步加载 +回调显示
 * 3 预期显示尽可能的快。
 * LIFO FIFO
 * <p>
 * keywords -> datas
 * keywords-> lruCache查找
 * ->找到返回
 * ->找不到  keyword -> task ->TaskQueue 且发送一个通知，提醒轮询线程
 * <p>
 * Task -> Run {
 * 放入Lrucache
 * }
 * <p>
 * 后台轮训线程
 * TaskQueue -> Task -> 线程池执行
 * <p>
 * handler + looper + message
 */

public class SuggestLoader {

    //1 实现单例
    private LruCache<String, Object> mMemoryCache;
    private ExecutorService mThreadPool;

    private Type mType = Type.LIFO;
    private LinkedList<Runnable> mTaskQueue;

    public enum Type {
        FIFO,
        LIFO
    }

    //后台轮线程 + ui

    private SuggestLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    private void init(int threadCount, Type type) {
        //初始化/后台轮线程HandlerThread

        //线程池中取出一个任务执行。


        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory /8;
        mMemoryCache = new LruCache<String,Object>(cacheMemory){
        };

        mThreadPool  = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<Runnable>();
        mType = type;
    }

    public void load(final String keyWords){

       final Object data =  getDataFromLruCache(keyWords);
       if (data != null){
           //更新。Ui
           //                   refresh();
       }else {
           addTask(new Runnable() {
               @Override
               public void run() {

                   addDataToLruCache(keyWords,new Object());

//                   refresh();
               }


           });
       }
    }
    private void addDataToLruCache(String keyWords, Object o) {
        if (getDataFromLruCache(keyWords) == null){
            if (o != null){
                mMemoryCache.put(keyWords,o);
            }
        }
    }

    private synchronized void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);

    }

    private Runnable getTask(){
        if (mType == Type.FIFO){
            return mTaskQueue.removeFirst();
        }else {
            return mTaskQueue.removeLast();
        }

    }

    private Object getDataFromLruCache(String keyWords) {
        return mMemoryCache.get(keyWords);
    }
}
