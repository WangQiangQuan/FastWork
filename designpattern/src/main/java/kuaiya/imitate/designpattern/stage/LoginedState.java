package kuaiya.imitate.designpattern.stage;

import android.util.Log;

/**
 * 已经登录
 */

public class LoginedState implements UserState {
    private static final String TAG = "LoginedState";
    @Override
    public void forward() {
        Log.i(TAG, "forward: ");
    }

    @Override
    public void comment() {
        Log.i(TAG, "comment: ");
    }
}
