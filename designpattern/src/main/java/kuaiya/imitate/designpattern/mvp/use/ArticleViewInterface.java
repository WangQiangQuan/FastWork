package kuaiya.imitate.designpattern.mvp.use;

import java.util.List;

/**
 * 主界面的逻辑接口
 */

public interface ArticleViewInterface {
    public void showLoading();
    public void hideLoading();

    public void showArticles(List<Article> articles);//展示数据
}
