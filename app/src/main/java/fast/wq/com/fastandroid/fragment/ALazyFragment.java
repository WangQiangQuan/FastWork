package fast.wq.com.fastandroid.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by admin on 2017/9/1.
 */

public abstract class ALazyFragment extends Fragment {

    //是否可见
    private boolean isVisiable;
    // 标志位，标志已经初始化完成。onCreateView后调用
    public boolean isPrepared;

    /**
     * 该方法用于告诉系统，这个Fragment的UI是否是可见的。
     * setUserVisibleHint是在onCreateView之前调用的
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisiable = true;
            onVisible();
        } else {
            isVisiable = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {}

    // lazyLoad 的时候调用
    public boolean isCanLoad() {
        if (isPrepared && isVisiable) {
            return true;
        }
        return false;
    }
}
