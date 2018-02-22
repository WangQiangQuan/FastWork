package fast.wq.com.fastandroid;

import android.app.Application;
import android.util.Log;

import fast.wq.com.fastandroid.gloable.GlobalStates;

/**
 * Created by admin on 2018/2/13.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();

        GlobalStates.setContext(this);
        Log.i(TAG, "onCreate: ");
    }
}
