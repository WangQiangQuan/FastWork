package fast.wq.com.fastandroid.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import fast.wq.com.fastandroid.ProcessConnection;

/**
 * 5.0以下使用
 * 双进程守护
 * 主要利用 杀死线程的时候是一个接着一个杀死
 */

public class GuardService extends Service {

    private final int GuardId = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //提升优先级
      //  startForeground(GuardId, new Notification());
        //
        Intent m = new Intent(this, MessageService.class);
        bindService(m, mServiceConnection, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ProcessConnection.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
        };
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            //断开连接
            Intent m = new Intent(GuardService.this, MessageService.class);
            startService(m);

            bindService(m, mServiceConnection, Context.BIND_IMPORTANT);
        }
    };
}
