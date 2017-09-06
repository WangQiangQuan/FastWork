package fast.wq.com.fastandroid.utils;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * 使用场景
 *某个操作需要的资源初始化完毕
  某个服务依赖的线程全部开启等等...
 */

public class CountDownLatchUtils {
    private static final String TAG = "CountDownLatchUtils";
    private static CountDownLatch latch = new CountDownLatch(3);
    public static void go(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "out run() called :"+Thread.currentThread().getName());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "in 1 run() called :"+Thread.currentThread().getName());
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "in 2 run() called :"+Thread.currentThread().getName());
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "in 3 run() called :"+Thread.currentThread().getName());
                        latch.countDown();
                    }
                }).start();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "资源准备完成后才可以执行 run() called");
            }
        }).start();
    }
}
/**
 * 09-06 10:47:16.416 6929-6971/fast.wq.com.fastandroid D/CountDownLatchUtils: out run() called :Thread-3971
 09-06 10:47:16.429 6929-6973/fast.wq.com.fastandroid D/CountDownLatchUtils: in 2 run() called :Thread-3973
 09-06 10:47:16.430 6929-6974/fast.wq.com.fastandroid D/CountDownLatchUtils: in 3 run() called :Thread-3974
 09-06 10:47:16.529 6929-6972/fast.wq.com.fastandroid D/CountDownLatchUtils: in 1 run() called :Thread-3972
 09-06 10:47:16.530 6929-6971/fast.wq.com.fastandroid D/CountDownLatchUtils: 资源准备完成后才可以执行 run() called
 */