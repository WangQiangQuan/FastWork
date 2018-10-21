package fast.wq.com.fastandroid.permissions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import fast.wq.com.fastandroid.R;

public class PerActivity extends AppCompatActivity implements PermiUtils.OnRequestPermissionsResultCallbacks {
    private static final String TAG = "PerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per);

        PermiUtils.getCameraPermissions(this,100);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        Log.e(TAG,"同意:" + perms.size() + "个权限,isAllGranted=" + isAllGranted);
        for (String perm : perms) {
            Log.e(TAG,"同意:" + perm);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {
        Log.e(TAG,"拒绝:" + perms.size() + "个权限,isAllDenied=" + isAllDenied);
        for (String perm : perms) {
            Log.e(TAG,"拒绝:" + perm);
        }
    }
}
