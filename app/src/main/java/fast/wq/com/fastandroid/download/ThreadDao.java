package fast.wq.com.fastandroid.download;

import java.util.List;

/**
 数据访问接口
 */

public interface ThreadDao {

    /**
     * 插入
     * @param mThreadinfo
     */
    public void insertThread(ThreadInfo mThreadinfo);
    /**
     * 删除
     */
    public  void deleteThread(String url ,int thread_id);
    /**
     * 更新
     */
    public  void updateThread(String url ,int thread_id , int finished);
    /**
     * 查找
     */
    public List<ThreadInfo> getThreads(String url);
    /**
     *s 是否已经存在
     *
     */
    public boolean isExiste(String url ,int thread_id);
}
