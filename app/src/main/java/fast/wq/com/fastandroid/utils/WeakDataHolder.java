package fast.wq.com.fastandroid.utils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * http://blog.csdn.net/dayun220/article/details/77720473
 * 也可以解决
 * Intent传递数据量过大的问题
 */

public class WeakDataHolder {
    private static WeakDataHolder instance;

    public static WeakDataHolder getInstance(){
        if(instance == null){
            synchronized (WeakDataHolder.class) {
                if (instance == null) {
                    instance = new WeakDataHolder();
                }
            }
        }
        return instance;
    }

    private Map<String, WeakReference<Object>> map = new HashMap<>();

    /**
     * 数据存储
     * @param id
     * @param object
     */
    public void saveData(String id, Object object) {
        map.put(id, new WeakReference<>(object));
    }

    /**
     * 获取数据
     * @param id
     * @return
     */
    public Object getData(String id) {
        WeakReference<Object> weakReference = map.get(id);
        return weakReference.get();
    }
}
