package fast.wq.com.fastandroid.view.swiplayout.tools;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.Stack;

public class WxSwipeBackActivityManager extends ActivityLifecycleCallbacksAdapter {
    private static final WxSwipeBackActivityManager instance = new WxSwipeBackActivityManager();
    private Stack<Activity> mActivityStack = new Stack<>();
    private WeakReference<Activity> mActivityResumed;

    private WxSwipeBackActivityManager() {
    }

    public static WxSwipeBackActivityManager getInstance() {
        return instance;
    }

    public void init(Application mApplication) {
        mApplication.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mActivityResumed = new WeakReference<Activity>(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityStack.remove(activity);
    }

    /**
     * 获取倒数第二个Activity
     *
     * @return
     */
    public Activity getPenultimateActivity() {
        Activity activity = mActivityStack.size() >= 2 ? mActivityStack.get(mActivityStack.size() - 2) : null;
        if (mActivityResumed != null && activity == mActivityResumed.get()) {
            activity = mActivityStack.size() >= 3 ? mActivityStack.get(mActivityStack.size() - 3) : null;
        }
        return activity;
    }


}
