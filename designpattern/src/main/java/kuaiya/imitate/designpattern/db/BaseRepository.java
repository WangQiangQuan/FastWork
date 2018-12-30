package kuaiya.imitate.designpattern.db;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangqiang on 2018/12/3.
 * 1 数据提供类，
 * 增强业务性比较强的数据请求，从而减少数据请求。
 */

public class BaseRepository {
    private static final String TAG = "BaseRepository";
    private ArrayList<WeakReference<OnResultChangeListener>> lists = new ArrayList<>();

    public BaseRepository() {

    }

    public synchronized boolean registerListener(OnResultChangeListener listener) {
        boolean result = false;
        boolean contains = false;
        contains = lists.contains(listener);
        if (!contains) {
            result = lists.add(new WeakReference<OnResultChangeListener>(listener));
        }


        return result;
    }

    public void unRegister(OnResultChangeListener listener) {
        Iterator<WeakReference<OnResultChangeListener>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            WeakReference<OnResultChangeListener> weakReference = iterator.next();
            if (weakReference.get() == listener) {
                weakReference.clear();
                iterator.remove();
            }
        }
    }

    public interface OnResultChangeListener<T> {
        void onResult(int dataType, T models);
    }

    public ConcurrentHashMap<Integer, BaseRepository> instanceMap = new ConcurrentHashMap<>();

    public BaseRepository getInstance(int type) {
        BaseRepository instance = instanceMap.get(type);

        if (instance == null) {
            BaseRepository targetInstance = null;
            switch (type) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            instanceMap.put(type, targetInstance);
        }
        return instanceMap.get(type);
    }
}
