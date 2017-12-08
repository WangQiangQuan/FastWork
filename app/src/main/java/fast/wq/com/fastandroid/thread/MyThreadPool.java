package fast.wq.com.fastandroid.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1 ->10
 * 最低19
 * int THREAD_PRIORITY_AUDIO //标准音乐播放使用的线程优先级

 　　int THREAD_PRIORITY_BACKGROUND //标准后台程序

 　　int THREAD_PRIORITY_DEFAULT // 默认应用的优先级

 　　int THREAD_PRIORITY_DISPLAY //标准显示系统优先级，主要是改善UI的刷新

 　　int THREAD_PRIORITY_FOREGROUND //标准前台线程优先级

 　　int THREAD_PRIORITY_LESS_FAVORABLE //低于favorable

 　　int THREAD_PRIORITY_LOWEST //有效的线程最低的优先级

 　　int THREAD_PRIORITY_MORE_FAVORABLE //高于favorable

 　　int THREAD_PRIORITY_URGENT_AUDIO //标准较重要音频播放优先级

 　　int THREAD_PRIORITY_URGENT_DISPLAY //标准较重要显示优先级，对于输入事件同样
 */
public class MyThreadPool {
    /** Thread factory */
    public static final MyThreadFactory threadFactory = new MyThreadFactory();
    public static final MyThreadLowFactory threadFactoryLow = new MyThreadLowFactory();


    /** High level Thread pool executor eg: access Internet and response interacts with UI*/
    public static ExecutorService EXECUTOR = new ThreadPoolExecutor(1, 4, 30L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), threadFactory);

    /**Low level just do work eg: send to server not respond from server,or response from server but not interact with UI*/
    public static ExecutorService LOW_PRIPRIOTY_EXECUTOR = new ThreadPoolExecutor(1, 4, 30L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), threadFactoryLow);

    /** A private thread factory */
    public static class MyThreadFactory implements ThreadFactory {
        AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "MyThreadPool-"+count.getAndIncrement()){
                @Override
                public void run() {
                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                    super.run();
                }
            };
            return t;
        }
    }

    /** A private thread factory */
    public static class MyThreadLowFactory implements ThreadFactory {
        AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "MyThreadLowPool-"+count.getAndIncrement()){
                @Override
                public void run() {
                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_LOWEST);
                    super.run();
                }
            };
            t.setPriority(Thread.MIN_PRIORITY);
            return t;
        }
    }

}
