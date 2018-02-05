package fast.wq.com.fastandroid.utils;

import android.util.Log;
import android.util.SparseArray;

import fast.wq.com.fastandroid.badge.BadgeMessage;

/**
 * SparseIntArray SparseBooleanArray SparseLongArray
 * 添加：如果key存在，则会执行Update。
 * void append (int key, E value)
  void put (int key, E value)
   删除：
 void delete (int key)
 void remove(int key)
 remove调用的是delete方法。

 void removeAt(int index) <strong>注意参数的区别</strong>
 void clear() 清空所有value

 在高版本的API中，还提供了方法
 removeAtRange(int index, int size) 清空一段范围内的value

 修改：
 void setValueAt (int index, E value),该方法必须保证其索引处存在key值，否则无效。
 查询：
 E get(int key)
 E get(int key, E valueIfKeyNotFound)

 其他：
 int size() 长度
 int keyAt(int index) 返回key值，可用于遍历

 用时间换空间
 */

public class SparseArrayUtils {
    private static final String TAG = "sparse";

    /**
     * test1: {1=badgeType:1,unreadMsgCount:0,extraCount:0}
       test2: {1=badgeType:2,unreadMsgCount:0,extraCount:0}
     */
    public void test(){
        BadgeMessage message1 = new BadgeMessage();
        message1.badgeType = 1;

        BadgeMessage message2 = new BadgeMessage();
        message2.badgeType = 2;

        BadgeMessage message3 = new BadgeMessage();
        message3.badgeType = 3;

        SparseArray<BadgeMessage> spraseArray = new SparseArray<>();

        spraseArray.append(1,message1);
        Log.i(TAG, "test1: "+spraseArray);
        //void setValueAt (int index, E value),该方法必须保证其索引处存在key值，否则无效。
        spraseArray.setValueAt(0,message2);
        spraseArray.setValueAt(1,message3);

        Log.i(TAG, "test2: "+spraseArray);
    }


    /**
     * SparseArray是以纯数组的形式存储的，一个数组存储的是key值一个数组存储的是value值，今天我们分析的ArrayMap和SparseArray有点类似，他也是以纯数组的形式存储，不过不同的是他的一个数组存储的是Hash值另一个数组存储的是key和value，其中key和value是成对出现的，key存储在数组的偶数位上，value存储在数组的奇数位上
     */
}
