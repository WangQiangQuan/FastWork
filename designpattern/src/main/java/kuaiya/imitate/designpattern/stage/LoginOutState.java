package kuaiya.imitate.designpattern.stage;

import android.util.Log;

/**
 * 未登录
 */

public class LoginOutState  implements UserState{
    private static final String TAG = "LoginOutState";
    @Override
    public void forward() {
        Log.i(TAG, "comment: 未登录请先登录");
    }

    @Override
    public void comment() {
        Log.i(TAG, "comment: 未登录请先登录");
    }
}
