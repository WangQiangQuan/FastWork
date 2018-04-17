package kuaiya.imitate.designpattern.mvp.use;

import android.os.Bundle;
import java.util.List;
import kuaiya.imitate.designpattern.R;
import kuaiya.imitate.designpattern.mvp.MVPBaseActivity;

/**
 * 使用封装构建mvp
 */
public class MvpActivity
        extends MVPBaseActivity<ArticleViewInterface, ArticleP2>
        implements ArticleViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
    }

    @Override
    protected ArticleP2 createPresenter() {
        return new ArticleP2();
    }


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
