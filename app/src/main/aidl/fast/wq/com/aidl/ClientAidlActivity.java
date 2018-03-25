package fast.wq.com.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fast.wq.com.fastandroid.R;

/**
 * （1）在服务端注册Service，并且需要android:exported="true"和android:process=":remote"属性。
 *
 * Aidl默认支持数据类型
 * 基本数据类型 (short除外)
 * List，map
 * Parcelable
 */
public class ClientAidlActivity extends AppCompatActivity {

    private int a1, a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_aidl);
        bindService();

    }

    private void bindService() {
        Intent mIntent = new Intent();
        mIntent.setComponent(new ComponentName("fast.wq.com.aidl", "fast.wq.com.aidl.IRemoteService"));
        bindService(mIntent, conn, Context.BIND_AUTO_CREATE);
    }

    private void calculate(){
        try {
           int result =  mAidl.add(1,1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    IAddAidl mAidl = null;
    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //拿到了远程服务
            mAidl = IAddAidl.Stub.asInterface(service);
            //客户端绑定服务成功后，设置死亡代理
            try {
                service.linkToDeath(mDeth,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };
    /**
     * 设置死亡代理
     * 当服务端断开我们可以收到通知
     */
    private IBinder.DeathRecipient mDeth = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mAidl == null){
                return;
            }

            mAidl.asBinder().unlinkToDeath(mDeth,0);
            mAidl = null;

            //重新绑定
        }
    };
}
