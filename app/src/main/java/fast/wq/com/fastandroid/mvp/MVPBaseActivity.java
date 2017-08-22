package fast.wq.com.fastandroid.mvp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by wangqiang on 2017/8/21.
 *
 */

public abstract class MVPBaseActivity<V ,T extends  BasePresenter<V>> extends Activity {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter  = createPresenter();
        mPresenter.attchView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
