package fast.wq.com.fastandroid.permissions.andpermission.target;

import android.content.Context;
import android.content.Intent;

/**
 * Created by admin on 2017/9/30.
 */

public class ActivityTarget implements ITarget {
    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void startActivity(Intent intent) {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }
}
