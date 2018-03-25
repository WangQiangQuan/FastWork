package fast.wq.com.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;

import fast.wq.com.fastandroid.bean.ParcelableBean;


/**
 * Created by wangqiang on 2018/3/25.
 */

public class IRemoteService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IAddAidl.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1+num2;
        }

    };

    List<Persion> lists;
    private IBinder beanBinder = new IPersionAidl.Stub() {

        @Override
        public List<Persion> addbean(Persion bean) throws RemoteException {
            lists.add(bean);
            return lists;
        }
    };

}
