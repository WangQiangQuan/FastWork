package fast.wq.com.fastandroid.thread;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 2018/3/7.
 */

public class TestThread extends Thread {
    private static final String TAG = "TestThread";
    private final BlockingQueue<String> mQueue =null;
    private volatile boolean mQuit = false;
    @Override
    public void run() {
      while (true){
          Log.i(TAG, "run: 1");
          while(true) {
              Log.i(TAG, "run: 2");
              try {
                  if (mQueue!= null)
                  mQueue.take();
                  break;
              } catch (InterruptedException var6) {
                  Log.i(TAG, "run: errror");
              }

              Log.i(TAG, "run: 3");
          }


      }
    }

    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }
}
