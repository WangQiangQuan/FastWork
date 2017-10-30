package fast.wq.com.fastandroid.permissions;

import android.os.Build;

/**
 * 兼容处理小米权限问题
 * http://blog.csdn.net/qq_30167925/article/details/53955026
 */

public class PermissionsUtils {

    public static boolean isNeedPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            return true;
        }
        return false;
    }
}
