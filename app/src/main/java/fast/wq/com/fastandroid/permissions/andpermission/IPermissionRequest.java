package fast.wq.com.fastandroid.permissions.andpermission;

import android.support.annotation.NonNull;

/**
 * Created by admin on 2017/9/30.
 */

public interface IPermissionRequest {
    @NonNull
    IPermissionRequest permission(String... permissions);

    @NonNull
    IPermissionRequest permission(String[]... permissionsArray);

    @NonNull
    IPermissionRequest requestCode(int requestCode);

    /* 用户拒绝过 */
//    @NonNull
    IPermissionRequest rationale(RationaleListener listener);

    IPermissionRequest callback(Object callback);

    @Deprecated
    void send();

    /**
     * Request permission.
     */
    void start();

}
