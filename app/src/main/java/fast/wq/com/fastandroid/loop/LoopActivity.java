package fast.wq.com.fastandroid.loop;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import fast.wq.com.fastandroid.R;

/**
 * Android定时器AlarmManager
 * AlarmManager是Android的全局定时器。
 * 就是在指定时间做一个事情（封装在PendingIntent）。通过PendingIntent的getActivity()、getService()或getBroadcast()来执行。
 * 听起来AlarmManager和Timer很类似，但是Timer有可能因为手机休眠而被杀掉服务，但是AlarmManager可以做到唤醒手机。
 */

public class LoopActivity extends AppCompatActivity {
    private static final String TAG = "wang";
    public static final String INTENT_ALARM_LOG = "intent_alarm_log";
    AlarmManager am;
    PendingIntent pi;
    CountDownTimerUtils m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        Log.i(TAG, "onCreate: ");
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(INTENT_ALARM_LOG);
        pi = PendingIntent.getBroadcast(LoopActivity.this, 0, intent, 0);
//        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+2000, pi);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2000, 2000, pi);


//        CountDownTimerUtils.test();
//         m=  CountDownTimerUtils.getCountDownTimer();
//                m.setMillisInFuture(5000)
//                .setFinishDelegate(new CountDownTimerUtils.FinishDelegate() {
//                    @Override
//                    public void onFinish() {
//                        Log.v("CountDownTimerUtils", "onFinish");
//                    }
//                }).start();

        TimerUtils.test();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        am.cancel(pi);
        if (m !=null){
            m.cancel();
        }
    }

    public static class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == LoopActivity.INTENT_ALARM_LOG) {
                Log.d(TAG, "log log log");
            }

        }
    }

}
