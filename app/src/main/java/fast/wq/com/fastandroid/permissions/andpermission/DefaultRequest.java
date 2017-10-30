package fast.wq.com.fastandroid.permissions.andpermission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fast.wq.com.fastandroid.permissions.andpermission.target.ITarget;

/**
 * Created by admin on 2017/9/30.
 */

public class DefaultRequest implements
        IPermissionRequest,
        Rationale {
    private static final String TAG = "DefaultRequest";

    private ITarget target;
    private int mRequestCode;
    private String[] mPermissions;
    private Object mCallback;
    private RationaleListener mRationaleListener;

    private String[] mDeniedPermissions;

    public DefaultRequest(ITarget target) {
        this.target = target;
    }

    @NonNull
    @Override
    public IPermissionRequest permission(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    @NonNull
    @Override
    public IPermissionRequest permission(String[]... permissionsArray) {
        List<String> permissionList = new ArrayList<>();
        for (String[] permissions : permissionsArray) {
            for (String permission : permissions) {
                permissionList.add(permission);
            }
        }
        this.mPermissions = permissionList.toArray(new String[permissionList.size()]);
        return this;
    }

    @NonNull
    @Override
    public IPermissionRequest requestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    @Override
    public IPermissionRequest rationale(RationaleListener listener) {
        this.mRationaleListener = listener;
        return this;
    }

    @Override
    public IPermissionRequest callback(Object callback) {
        this.mCallback = callback;
        return this;
    }

    @Override
    public void send() {
        start();
    }

    @Override
    public void start() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callbackSucceed();
        } else {
            mDeniedPermissions = getDeniedPermissions(target.getContext(), mPermissions);
            if (mDeniedPermissions.length > 0) {
                // Remind users of the purpose of mPermissions.
//                PermissionActivity.setRationaleListener(this);
//                Intent intent = new Intent(target.getContext(), PermissionActivity.class);
//                intent.putExtra(PermissionActivity.KEY_INPUT_PERMISSIONS, mDeniedPermissions);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                target.startActivity(intent);
            } else { // All permission granted.
                callbackSucceed();
            }
        }
    }


    @Override
    public void cancel() {

    }

    @Override
    public void resume() {

    }

    private boolean isNeedRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        return true;

    }

    private void callbackSucceed() {
        if (mCallback != null) {
            if (mCallback instanceof PermissionListener)
                ((PermissionListener) mCallback).onSucceed(mRequestCode, Arrays.asList(mPermissions));
            else {
//                callbackAnnotation(mCallback, mRequestCode, PermissionYes.class, Arrays.asList(mPermissions));
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private static String[] getDeniedPermissions(Context context, @NonNull String... permissions) {
        List<String> deniedList = new ArrayList<>(1);
        for (String permission : permissions)
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                deniedList.add(permission);
        return deniedList.toArray(new String[deniedList.size()]);
    }

}
