package fast.wq.com.fastandroid.utils;

import android.os.Looper;

/**
 * Created by admin on 2017/10/30.
 */

public class JudgeUtils {
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
