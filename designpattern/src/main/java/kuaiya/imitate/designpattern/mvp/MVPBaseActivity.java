package kuaiya.imitate.designpattern.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 生命周期控制 presenter的关系
 * 避免子类重复代码。
 */

public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends Activity {
    protected T mPresennter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresennter = createPresenter();
        mPresennter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresennter.detachView();
    }

    protected abstract T createPresenter();
}
