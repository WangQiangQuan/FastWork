package fast.wq.com.fastandroid.permissions.andpermission.target;

import android.content.Context;
import android.content.Intent;

/**
 * ActivityTarget
 * Fragment
 * 等的 接口
 */

public interface ITarget {

    Context getContext();

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);
}
