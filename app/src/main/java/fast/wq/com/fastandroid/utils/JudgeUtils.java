package fast.wq.com.fastandroid.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Looper;

/**
 * Created by admin on 2017/10/30.
 */

public class JudgeUtils {
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing())
            return true;
        else {
            if (isDestroyed(activity)) {
                return true;
            }
        }
        return false;
    }

    /***
     * activity.isDestroyed()该函数在 api level >=17 以上才有
     * @param activity
     * @return
     */
    private static boolean isDestroyed(Activity activity) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            return true;
        }
        return false;
    }
}
