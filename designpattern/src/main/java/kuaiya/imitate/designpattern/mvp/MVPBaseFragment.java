package kuaiya.imitate.designpattern.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by wangqiang on 2018/9/16.
 */

public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected T mPresennter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresennter = createPresenter();
        mPresennter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresennter.detachView();
    }

    protected abstract T createPresenter();
}
