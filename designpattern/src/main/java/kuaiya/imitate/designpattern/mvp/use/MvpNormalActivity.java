package kuaiya.imitate.designpattern.mvp.use;
import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import kuaiya.imitate.designpattern.R;

public class MvpNormalActivity extends Activity
        implements ArticleViewInterface{

    private ArticlePresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_normal);

        //建立 view和 p的关系
        mPresenter = new ArticlePresenter(this);
        mPresenter.fetchArticles();
    }


    //------------mvp function view---------------
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showArticles(List<Article> articles) {

    }
}
