package fast.wq.com.fastandroid.thread.syn;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 */

public class BlockingTest {
    private static final String TAG = "BlockingTest";

    public  void testMain() {
//        BlockingQueue<String> a = new ArrayBlockingQueue<String>(1);
        ArrayBlockingQueue<String> mbq = new ArrayBlockingQueue<>(1);
        //启动三个生产者线程
        new Producter(mbq).start();
        new Producter(mbq).start();
        new Producter(mbq).start();
        //启动一个消费者线程
        new Consumer(mbq).start();
    }

    class Producter extends Thread {
        private ArrayBlockingQueue<String> bq;


        public Producter(ArrayBlockingQueue<String> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            String[] arry = new String[]{"java", "thread", "bq"};
            for (int i = 0; i < 999999; i++) {
                Log.i(TAG, "run: 生产者准备生产者元素");

                String value = arry[i % 3];
                try {
                    Thread.sleep(200);
                    bq.put(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Log.i(TAG, "run: " + getName() + "生产完成" + bq);
        }
    }


    class Consumer extends Thread {
        private ArrayBlockingQueue<String> bq;

        public Consumer(ArrayBlockingQueue<String> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            while (true) {
                Log.i(TAG, "run: 消费者准备消费集合元素");
                try {
                    Thread.sleep(200);
                    bq.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
