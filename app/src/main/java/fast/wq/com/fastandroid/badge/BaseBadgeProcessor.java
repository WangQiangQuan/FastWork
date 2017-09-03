package fast.wq.com.fastandroid.badge;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin on 2017/9/3.
 */

public abstract class BaseBadgeProcessor implements BadgeOperation {

    List<BadgeChangedListener> mList_listener;
    protected int type;
    protected Context mContext;
    protected Handler mMainHandler;
    protected BadgeMessage mBadgeMessage;

    public BaseBadgeProcessor(Context context, int type) {
        this.type = type;
        this.mContext = context;
        mList_listener = new LinkedList<>();
        mMainHandler = new Handler(Looper.myLooper());
    }

    @Override
    public void registerBadgeChangeListener(BadgeChangedListener listener) {

        if (!mList_listener.contains(listener)) {
            mList_listener.add(listener);
        }
    }

    @Override
    public void unregisterBadgeChangeListener(BadgeChangedListener listener) {
        mList_listener.remove(listener);
    }

    @Override
    public void destroy() {
        mList_listener.clear();
        mMainHandler.removeCallbacksAndMessages(null);
    }

    protected void notifyAllChanged(final BadgeMessage mBadge) {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                mBadgeMessage = mBadge;
                List<BadgeChangedListener> list = new ArrayList<BadgeChangedListener>(mList_listener);
                for (BadgeChangedListener listener : list) {
                    listener.onBadgeChanged(mBadgeMessage);
                }
            }
        });
    }
}
