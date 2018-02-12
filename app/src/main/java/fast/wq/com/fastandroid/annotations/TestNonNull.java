package fast.wq.com.fastandroid.annotations;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by admin on 2018/2/12.
 */

public class TestNonNull {
    private static final String TAG = "TestNonNull";

    public static void test(NonNull String ) {
        Log.i(TAG, "test: " );
    }

    public static String go(int i) {
        if (i == 0) {
            return "";
        } else {
            return null;
        }
    }
}
