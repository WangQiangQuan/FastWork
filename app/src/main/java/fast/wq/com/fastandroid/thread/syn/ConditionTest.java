package fast.wq.com.fastandroid.thread.syn;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 常用的就是await与signal。
 这个两个就刚好对应object的wait与notify。
 condition一方面是对lock功能的补充
 主线程运行10次,然后子线程2运行20次,接着子线程3运行30次
 上面的整体运行4次
 */

public class ConditionTest {

    private static final String TAG = "ConditionTest";
    public static void test() {
        final Business business = new Business();
        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        for (int i = 1; i <= 4; i++) {
                            business.sub2(i);
                        }

                    }
                }
        ).start();

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        for (int i = 1; i <= 4; i++) {
                            business.sub3(i);
                        }

                    }
                }
        ).start();

        for (int i = 1; i <= 4; i++) {
            business.main(i);
        }
    }

    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        private int shouldSub = 1;

        public void sub2(int i) {
            lock.lock();
            try {
                while (shouldSub != 2) {
                    try {
                        condition2.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 20; j++) {
                    Log.i(TAG, "sub2 thread sequence of " + j + ",loop of " + i);
                    System.out.println("sub2 thread sequence of " + j + ",loop of " + i);
                }
                shouldSub = 3;
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }


        public void sub3(int i) {
            lock.lock();
            try {
                while (shouldSub != 3) {
                    try {
                        condition3.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 30; j++) {
                    Log.i(TAG, "sub3 thread sequence of " + j + ",loop of " + i);
                    System.out.println("sub3 thread sequence of " + j + ",loop of " + i);
                }
                shouldSub = 1;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (shouldSub != 1) {
                    try {
                        condition1.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    Log.i(TAG, "main thread sequence of " + j + ",loop of " + i);
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                shouldSub = 2;
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
