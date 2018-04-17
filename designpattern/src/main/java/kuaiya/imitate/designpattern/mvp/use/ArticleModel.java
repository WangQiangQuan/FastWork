package kuaiya.imitate.designpattern.mvp.use;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于保存网络数据
 * 到数据库
 */

public interface ArticleModel {
    List<Article> mCachedArticles = new ArrayList<>();
    public void saveArticles(List<Article> articles);
    public List<Article> loadArticlesFromCache();
}
