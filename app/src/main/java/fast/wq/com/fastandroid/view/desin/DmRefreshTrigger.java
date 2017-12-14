package fast.wq.com.fastandroid.view.desin;

/**
 * Created by admin on 2017/12/14.
 */

public interface DmRefreshTrigger {
    /**
     * 下拉状态
     * @param progress  下拉距离相对触发点的百分比
     */
    void onPullDownState(float progress);

    /**
     * 刷新中
     */
    void onRefreshing();

    /**
     * 释放即可触发刷新状态
     */
    void onReleaseToRefresh();

    /**
     * 完成
     */
    void onComplete();

    void init();
}
