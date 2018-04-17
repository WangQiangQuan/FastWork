package kuaiya.imitate.designpattern.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 防止内存泄漏
 * 通过弱引用和生命周期来结局。
 * 四个方法分别 建立关联，删除关联，判断关联，获取关联
 */

public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef;  // View 接口的弱引用

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
