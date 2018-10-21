package fast.wq.com.fastandroid.thread;


import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Handler 产生根本原因 ：activity中多个线程同时更新ui，并且没有加锁机制。
 *
 * postdelay https://blog.csdn.net/kesalin/article/details/37765707
 * https://blog.csdn.net/u013718120/article/details/53115824
 * https://blog.csdn.net/windskier/article/details/6995546
 *
 */

public class FHandler {
    private static final String TAG = "FHandler";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private static class MyHandler extends Handler {

        private WeakReference<Activity> activityWeakReference;

        public MyHandler(Activity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = activityWeakReference.get();
            if (activity != null) {
                if (msg.what == 1) {
                    // 做相应逻辑
                }
            }
        }
    }



    private void send(){
        mHandler.obtainMessage().sendToTarget();
    }

    private void lop(){
        Looper m;
    }
    /**
     * 创建与子线程相关的handler
     */
    class MyThread extends Thread {
        Handler mhandler = null;
        @Override
        public void run() {
            Looper.prepare();
            mhandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.i(TAG, "handleMessage: "+Thread.currentThread().getName());
                }
            };
            Looper.loop();
        }
    }

    private void test(){
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.mhandler.obtainMessage().sendToTarget();
    }

    private void remove(){
        mHHanler.removeCallbacksAndMessages(null);
        mHHanler.removeCallbacks(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    /**
     * HandlerThread
     */
    HandlerThread handlerThread ;
    Handler mHHanler;
    private void testHandlerThread(){
        handlerThread = new HandlerThread("name");
        handlerThread.start();

        mHHanler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "handleMessage: "+Thread.currentThread().getName());

            }
        };
        mHHanler.sendEmptyMessage(1);

        mHHanler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },100);
    }

    /**
     * 主线程和子线程handler 通信
     * 发给谁持有该线程的handler
     */

    /**
     * 常见的更新ui方式
     * runOnuiThread
     * post
     */
    public void update(Activity m,View v){
        mHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        mHandler.sendEmptyMessage(1);

        mHHanler.sendMessage(Message.obtain());

        m.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        /**
         * https://segmentfault.com/a/1190000011026324
         * 7.0后的改变
         */
        v.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
