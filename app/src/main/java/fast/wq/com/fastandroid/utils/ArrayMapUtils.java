package fast.wq.com.fastandroid.utils;

import android.support.v4.util.ArrayMap;

/**
 * 使用SparseArray和ArrayMap代替HashMap
 * https://segmentfault.com/a/1190000005052408#articleHeader16
 *https://blog.csdn.net/baidu_17508977/article/details/53380531
 * HashMap在扩容时采取的做法是：将当前的数据结构所占空间*2
 * SimpleArrayMap 扩容为一半
 * ArrayMap：ArrayMap继承了SimpleArrayMap，又实现了Map的接口；主要的操作，则是通过引入MapCollections类，使用Map中的Entry结构，这样在ArrayMap中就可以通过Iterator来进行数据的的迭代操作。
 */

public class ArrayMapUtils {
    public void test(){
        ArrayMap<String,String> map = new ArrayMap<>();
    }
}
