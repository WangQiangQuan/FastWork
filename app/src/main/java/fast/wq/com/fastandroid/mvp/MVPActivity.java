package fast.wq.com.fastandroid.mvp;


import android.os.Bundle;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.mvp.view.IViewInterface;

public class MVPActivity extends MVPBaseActivity<IViewInterface ,ImplPresenter> implements IViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
    }

    @Override
    protected ImplPresenter createPresenter() {
        return null;
    }

    @Override
    public void showDatas() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
