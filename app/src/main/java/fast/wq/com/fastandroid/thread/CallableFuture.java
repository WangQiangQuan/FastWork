package fast.wq.com.fastandroid.thread;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 */

public class CallableFuture {
    private static final String TAG = "wang";

    public void test() {

        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                for (; i < 100; i++) {
                    Log.i(TAG, "call: ="+i+" name = "+Thread.currentThread().getName());
                }
                return i;
            }
        });

        for (int i=0;i<100;i++){
            Log.i(TAG, "main: ="+i+" name = "+Thread.currentThread().getName());
            if (i == 20){
                new Thread(task,"又返回值的县城").start();
            }
        }
        try {
            Log.i(TAG, "test: "+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
