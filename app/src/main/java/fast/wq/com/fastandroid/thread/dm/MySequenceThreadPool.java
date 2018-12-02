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
public class MySequenceThreadPool {
    public static final MyThreadFactory threadFactory = new MyThreadFactory();

    /** Thread pool executor */
    public static ExecutorService EXECUTOR = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), threadFactory);

    /** A private thread factory */
    public static class MyThreadFactory implements ThreadFactory {
        AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "MySequenceThreadPool-"+count.getAndIncrement()){
                @Override
                public void run() {
                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                    super.run();
                }
            };
            return t;
        }
    }
}
