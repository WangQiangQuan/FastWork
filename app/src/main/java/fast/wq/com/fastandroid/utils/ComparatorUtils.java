package fast.wq.com.fastandroid.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * http://blog.csdn.net/u012250875/article/details/55126531
 * 1. 排序
 * 2. 分组
 */

public class ComparatorUtils {
    private static final String TAG = "ComparatorUtils";
    class Dog{
        public int age;
        public String name;
        public Dog(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }
        @Override
        public String toString() {
            return "Dog [age=" + age + ", name=" + name + "]";
        }
    }

    /**
     * 排序
     */
    public static void main() {
        List<Dog> list= new ArrayList<>();
        list.add(new ComparatorUtils().new Dog(5, "DogA"));
        list.add(new ComparatorUtils().new Dog(6, "DogB"));
        list.add(new ComparatorUtils().new Dog(7, "DogC"));
        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                return o2.age - o1.age;
            }
        });

        Log.i(TAG, "给狗狗按照年龄倒序: "+list);
        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        Log.i(TAG, "给狗狗按名字字母顺序排序: "+list);
    }

    /**
     * -------------------
     */
    public static void mainByGroup() {
        List<Dog> list= new ArrayList<>();
        list.add(new ComparatorUtils().new Dog(5, "DogA"));
        list.add(new ComparatorUtils().new Dog(5, "DogA"));
        list.add(new ComparatorUtils().new Dog(6, "DogB"));
        list.add(new ComparatorUtils().new Dog(6, "DogB"));
        list.add(new ComparatorUtils().new Dog(7, "DogC"));
        list.add(new ComparatorUtils().new Dog(7, "DogC"));
        List<List<Dog>> byAges = divider(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                // 按年龄分组
                return (o1.age == o2.age) ? 0 : 1;
            }
        });

        Log.i(TAG, "按年龄分组: "+byAges);

        List<List<Dog>> byNames = divider(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                // 按年龄分组
                return o1.name.compareTo(o2.name);
            }
        });
        Log.i(TAG, "按姓名分组: "+byNames);
    }

    /**
     * @author puyf
     * @Description:按条件分组
     * @param datas
     * @param c
     *            是否为同一组的判断标准
     * @return
     */
    public static <T> List<List<T>> divider(Collection<T> datas, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<List<T>>();
        for (T t : datas) {
            boolean isSameGroup = false;
            for (int j = 0; j < result.size(); j++) {
                if (c.compare(t, result.get(j).get(0)) == 0) {
                    isSameGroup = true;
                    result.get(j).add(t);
                    break;
                }
            }
            if (!isSameGroup) {
                // 创建
                List<T> innerList = new ArrayList<T>();
                result.add(innerList);
                innerList.add(t);
            }
        }
        return result;
    }

}
/**
 * 给狗狗按照年龄倒序: [Dog [age=7, name=DogC], Dog [age=6, name=DogB], Dog [age=5, name=DogA]]
 给狗狗按名字字母顺序排序: [Dog [age=5, name=DogA], Dog [age=6, name=DogB], Dog [age=7, name=DogC]]
 */
/**
 * 按年龄分组: [[Dog [age=5, name=DogA], Dog [age=5, name=DogA]], [Dog [age=6, name=DogB], Dog [age=6, name=DogB]], [Dog [age=7, name=DogC], Dog [age=7, name=DogC]]]
 按姓名分组: [[Dog [age=5, name=DogA], Dog [age=5, name=DogA]], [Dog [age=6, name=DogB], Dog [age=6, name=DogB]], [Dog [age=7, name=DogC], Dog [age=7, name=DogC]]]

 */
