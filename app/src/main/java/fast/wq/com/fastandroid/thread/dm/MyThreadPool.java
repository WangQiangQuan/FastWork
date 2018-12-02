package fast.wq.com.fastandroid.thread.dm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiabingchen on 15/10/12.
 */
public class MyThreadPool {
    /** Thread factory */
    public static final MyThreadFactory threadFactory = new MyThreadFactory();
    public static final MyThreadLowFactory threadFactory2 = new MyThreadLowFactory();

    /** High level Thread pool executor eg: access Internet and response interacts with UI*/
    public static ExecutorService EXECUTOR = new ThreadPoolExecutor(1, 4, 30L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), threadFactory);

    /**Low level just do work eg: send to server not respond from server,or response from server but not interact with UI*/
    public static ExecutorService LOW_PRIPRIOTY_EXECUTOR = new ThreadPoolExecutor(1, 4, 30L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), threadFactory2);

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
