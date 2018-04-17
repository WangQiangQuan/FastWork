package fast.wq.com.fastandroid.utils;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * java集合
 * set
 * list 元素有序，可重复
 * queue 先进先出的容器
 * map
 */

public class CollectionUtils {
    private static final String TAG = "CollectionUtils";

    /**
     * 1元素不重复，2不保证顺序4不同步 5可以为null
     */
    void mhashSet() {

    }

    /**
     * linkedhashSet
     * hashSet 的一个子类
     * 保证添加顺序
     */
    void molinkedhashSet() {

    }

    /**
     * TreeSet
     * 保证排序状态
     */
    void mTreeSet() {

        //定制排序
        TreeSet ts = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }
    /*********队列**************/
    /**
     * 优先队列
     * 顺序按照 大小排序
     * 不允许为null
     */
    void mPriorityQueue() {

    }

    /**
     * 双端队列
     */
    void mDeQue() {
        //当作栈来使用
        ArrayDeque stack = new ArrayDeque();
        stack.push("1");//相当于 addFirst
        stack.push("2");// 2 ,1 此时顺序为
    }

    /**
     * 实现了list和DeQue
     * 可以当队列和 栈
     */
    void mLinkedList() {
        LinkedList bo = new LinkedList();
    }

    /**********************Map*********************************/

    void mHashMap() {
        HashMap<String, Integer> m = new HashMap();
        m.put("a", 1);

        //放入相同的k
        int result = m.put("a", 2);//1返回之前的
        //便利

        for (Object key : m.keySet()) {
            Log.i(TAG, "mHashMap: " + "key" + key + "value=" + m.get(key));
        }
    }

    /**
     * 保证添加顺序
     */
    void mLinkedHashMap() {

    }

    /**********************Conllections*********************************/
    private void conllectionsutis(){
        ArrayList num = new ArrayList();
        Collections.reverse(num);//反转顺序
        Collections.shuffle(num);//随机顺序
        Collections.sort(num, new Comparator() {//排序
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });

        Collections.swap(num,0,1);//交换

        Collections.rotate(num,2);//从distance旋转

        //查找和替换
        //同步控制
        List list = Collections.synchronizedList(new ArrayList());
    }
}
