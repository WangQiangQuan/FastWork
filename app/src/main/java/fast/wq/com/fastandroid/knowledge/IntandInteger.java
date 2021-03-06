package fast.wq.com.fastandroid.knowledge;

/**
 * 1、Integer是int的包装类，int则是java的一种基本数据类型
 * 2、Integer变量必须实例化后才能使用，而int变量不需要
 * 3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值
 * 4、Integer的默认值是null，int的默认值是0
 */

public class IntandInteger {
    /**
     * 由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）。
     */
    private void test1() {
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.print(i == j); //false
    }

    /**
     * Integer变量和int变量比较时，只要两个变量的值是向等的，则结果为true（因为包装类Integer和基本数据类型int比较时，java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）
     */
    private void test2() {
        Integer i = new Integer(100);
        int j = 100;
        System.out.print(i == j); //true
    }

    /**
     * 非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。（因为非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同
     */
    private void test3() {
        Integer i = new Integer(100);
        Integer j = 100;
        System.out.print(i == j); //false
    }

    /**
     * 对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
     */
    private void test4() {
        Integer i = 100;
        Integer j = 100;
        System.out.print(i == j); //true
    }

    private void test5() {
        Integer i = 128;
        Integer j = 128;
        System.out.print(i == j); //false
    }
}
