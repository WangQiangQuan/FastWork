package kuaiya.imitate.designpattern.strategypattern;

import android.util.Log;

/**
 * 减少 if else 方法一：接口分层
 * 减少 if else 方法二：多态:允许将子类类型的指针赋值给父类类型的指针
 */

public class ShareManager {

    public void share(AShare item, ShareListener listener) {
        if (item == null) {
            if (listener != null) {
                listener.onCallback(ShareListener.STATE_FAIL, "ShareItemBean 不能为 null");
            }
            return;
        }
        if (listener == null) {
            listener = new ShareListener() {
                @Override
                public void onCallback(int state, String msg) {
                    Log.i("DEBUG", "ShareListener is null");
                }
            };
        }
        shareImpl(item, listener);
    }
    private void shareImpl (AShare item, ShareListener listener) {
        item.doShare(listener);
    }
}
