package fast.wq.com.fastandroid.permissions.andpermission;

import android.support.annotation.NonNull;

import java.util.List;

/**
 *
 */

public interface PermissionListener {

    void onSucceed(int requestCode, @NonNull List<String> grantPermissions);

    void onFailed(int requestCode, @NonNull List<String> deniedPermissions);
}
