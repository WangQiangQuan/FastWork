package fast.wq.com.fastandroid.thread;

import java.util.concurrent.Semaphore;

/**
 * 如何控制某个方法允许并发访问线程的个数？
 */

public class SemaphoreTest {

    Semaphore semaphore = new Semaphore(5,true);
    public void main(){

        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
    }

    public void test(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"进来了");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"离开了");
        semaphore.release();
    }
}
