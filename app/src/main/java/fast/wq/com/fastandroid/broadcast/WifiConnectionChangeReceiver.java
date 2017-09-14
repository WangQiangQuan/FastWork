package fast.wq.com.fastandroid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by admin on 2017/9/14.
 */

public class WifiConnectionChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "ConnectionChangeReceive";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {//这个监听wifi的打开与关闭，与wifi的连接无关
            Log.v(TAG, "收到WIFI_STATE_CHANGED_ACTION");
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1111);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.v(TAG, "收到" + "WIFI_STATE_DISABLED");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Log.v(TAG, "收到" + "WIFI_STATE_DISABLING");
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    Log.v(TAG, "收到" + "WIFI_STATE_ENABLED");
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    Log.v(TAG, "收到" + "WIFI_STATE_ENABLING");
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Log.v(TAG, "WIFI_STATE_UNKNOWN");

            }
        }

    }
    /**
     * 在无WIFI状态下打开WIFI 显示

     02-21 15:05:03.160: V/my2(13502): 收到WIFI_STATE_CHANGED_ACTION

     02-21 15:05:03.160: V/my2(13502): 收到WIFI_STATE_ENABLING

     02-21 15:05:05.246: V/my2(13502): 收到WIFI_STATE_CHANGED_ACTION

     02-21 15:05:05.246: V/my2(13502): 收到WIFI_STATE_ENABLED

     在 WIFI状态下关闭WIFI 显示

     02-21 15:12:18.168: V/my2(13502): 收到WIFI_STATE_CHANGED_ACTION

     02-21 15:12:18.183: V/my2(13502): 收到WIFI_STATE_DISABLING

     02-21 15:12:20.160: V/my2(13502): 收到WIFI_STATE_CHANGED_ACTION

     02-21 15:12:20.160: V/my2(13502): 收到WIFI_STATE_DISABLED
     */
}
