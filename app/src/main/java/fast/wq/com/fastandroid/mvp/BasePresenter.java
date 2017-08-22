package fast.wq.com.fastandroid.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by wangqiang on 2017/8/21.
 */

public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef; // View 接口类型的弱引用

    public void attchView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public void detachView() {
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    protected T getView() {
        return mViewRef.get();
    }
}
