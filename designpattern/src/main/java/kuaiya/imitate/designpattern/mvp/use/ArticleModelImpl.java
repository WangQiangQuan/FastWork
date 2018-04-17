package kuaiya.imitate.designpattern.mvp.use;

import java.util.List;

/**
 * Created by wangqiang on 2018/4/15.
 */

public class ArticleModelImpl implements ArticleModel{
    @Override
    public void saveArticles(List<Article> articles) {

    }

    @Override
    public List<Article> loadArticlesFromCache() {
        return null;
    }
}
