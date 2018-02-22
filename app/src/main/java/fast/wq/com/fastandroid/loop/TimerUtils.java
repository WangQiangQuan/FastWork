package fast.wq.com.fastandroid.loop;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer是Android直接启动定时器的类,
 * TimerTask是一个子线程，方便处理一些比较复杂耗时的功能逻辑
 */

public class TimerUtils {
    private static final String TAG = "TimerUtils";

    public static void test(){

        Timer mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "run: ");

            }
        };
        mTimer.schedule(mTimerTask,0,1000);
    }

    public interface  TimerProcessor {
        /**
         * 在TimerTask中执行的操作，由调用者定制
         */
        public  void process();
    }

    private TimerProcessor mProcessor;

    private int mDelayMs;

    private Timer mTimer;

    private TimerTask mTimerTask;

    /**
     * 构造函数
     * @param delayMs 延时
     * @param processor 定时处理器，由调用者定制实现
     */
    public TimerUtils(int delayMs, TimerProcessor processor) {
        mProcessor = processor;
        mDelayMs = delayMs;
    }

    /**
     * 启动定时器
     */
    public void startTimer() {
        mTimer = new Timer(true);
        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                if (mProcessor != null) {
                    mProcessor.process();
                }
            }

        };

        mTimer.schedule(mTimerTask, mDelayMs);
    }

    /**
     * 停止定时器
     */
    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }
}
