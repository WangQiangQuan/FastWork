package fast.wq.com.fastandroid.utils;

import android.util.Log;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier和CountDownLatch一样，都是关于线程的计数器。
 * 所有线程必须到齐了才可以越过障碍，越过障碍继续各自的工作线程
 */

public class CyclicBarrierUtils {
    private static final String TAG = "CyclicBarrierUtils";
    private static final int THREAD_NUM = 5;

    public static class WorkerThread implements Runnable {

        CyclicBarrier barrier;

        public WorkerThread(CyclicBarrier b) {
            this.barrier = b;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {

                Log.d(TAG, "Worker's waiting");
                //线程在这里等待，直到所有线程都到达barrier。
                barrier.await();
                Log.d(TAG, "ID:" + Thread.currentThread().getId() + " Working");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void go() {
        CyclicBarrier mCyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                //当所有线程到达barrier时执行
//                System.out.println("Inside Barrier");
                Log.d(TAG, "Inside Barrier");
            }
        });

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new WorkerThread(mCyclicBarrier)).start();
        }
    }

}
/**
 * 09-06 11:02:18.160 19880-19908/fast.wq.com.fastandroid D/CyclicBarrierUtils: Worker's waiting
 09-06 11:02:18.160 19880-19909/fast.wq.com.fastandroid D/CyclicBarrierUtils: Worker's waiting
 09-06 11:02:18.161 19880-19910/fast.wq.com.fastandroid D/CyclicBarrierUtils: Worker's waiting
 09-06 11:02:18.162 19880-19911/fast.wq.com.fastandroid D/CyclicBarrierUtils: Worker's waiting
 09-06 11:02:18.163 19880-19912/fast.wq.com.fastandroid D/CyclicBarrierUtils: Worker's waiting
 09-06 11:02:18.163 19880-19912/fast.wq.com.fastandroid D/CyclicBarrierUtils: Inside Barrier
 09-06 11:02:18.164 19880-19910/fast.wq.com.fastandroid D/CyclicBarrierUtils: ID:4009 Working
 09-06 11:02:18.164 19880-19909/fast.wq.com.fastandroid D/CyclicBarrierUtils: ID:4008 Working
 09-06 11:02:18.164 19880-19911/fast.wq.com.fastandroid D/CyclicBarrierUtils: ID:4010 Working
 09-06 11:02:18.164 19880-19908/fast.wq.com.fastandroid D/CyclicBarrierUtils: ID:4007 Working
 09-06 11:02:18.164 19880-19912/fast.wq.com.fastandroid D/CyclicBarrierUtils: ID:4011 Working
 */