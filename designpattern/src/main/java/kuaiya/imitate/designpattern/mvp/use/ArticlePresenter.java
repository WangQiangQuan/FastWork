package kuaiya.imitate.designpattern.mvp.use;

/**
 * p层持有v，m
 */

public class ArticlePresenter {
    ArticleViewInterface mArticleView;
    ArticleModel mArticleMode = new ArticleModelImpl();

    //从网络获取接口 暂略

    public ArticlePresenter(ArticleViewInterface mArticleView) {
        this.mArticleView = mArticleView;
    }
    //网络加载 fetch取得
    public void fetchArticles(){
        mArticleView.showLoading();
        //加载
        mArticleView.showArticles(null);
        mArticleView.hideLoading();
        mArticleMode.saveArticles(null);
    }

    public void loadArticlesFromDb(){
        mArticleMode.loadArticlesFromCache();
    }
}
