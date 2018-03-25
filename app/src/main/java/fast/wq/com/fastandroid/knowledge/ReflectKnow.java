package fast.wq.com.fastandroid.knowledge;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */

public class ReflectKnow {
    private static final String TAG = "ReflectKnow";

    public void test() {
        Class c1 = int.class; // int 的 类类型 class type
        Class c2 = String.class;

        Log.i(TAG, "test: " + c2.getName());
        Log.i(TAG, "test: " + c2.getSimpleName());


    }

    /**
     * 获取类方法的全部信息
     * String ->     String replace(char oldChar, char newChar)
     */
    private static void printClassMes(Object obj) {
        Class c = obj.getClass();

        Log.i(TAG, "printClassMes: 类的名称" + c.getName());

        /**
         *getMethods： 返回 public 函数，包括父类继承来的
         * getDeclaredMethods  返回 自己声明的。
         */
        Method[] ms = c.getMethods();
        c.getDeclaredMethods();
        for (int i = 0; i < ms.length; i++) {
            //返回方法 返回值类类型
            Class returnType = ms[i].getReturnType();
            Log.i(TAG, "printClassMes: " + returnType.getName());
            //返回参数类型
            Class[] parmTypes = ms[i].getParameterTypes();
            for (Class class1 = parmTypes) {
                Log.i(TAG, "printClassMes: " + class1.getName());
            }
        }
    }

    /**
     * 获取成员变量
     * getFields: 所有public 成员变量
     * getDeclaredFields 自己声明的
     * return :int MAX_VALUE
     */
    private void printMeb(Object obj) {
        Class c = obj.getClass();
        Field[] fs = c.getFields();
        Field[] fslefs = c.getDeclaredFields();

        for (Field field : fs) {
            Class filedType = field.getType();

            String typeName = filedType.getName();
            //成员变量的 名称
            String fileName = field.getName();
            Log.i(TAG, "printMeb: " + typeName + fileName);
        }
    }

    /**
     * 打印构造函数
     */

    private void prinConst(Object obj) {
        Class c = obj.getClass();
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor con : cs) {
            Log.i(TAG, "prinConst: " + con.getName());
            Class[] parmsType = con.getParameterTypes();
            for (Class class1 : parmsType) {
                Log.i(TAG, "prinConst: " + class1.getName());
            }
        }
    }

    /**
     * 方法的反射
     * 1方法的名称和方法的参数 列表才能唯一 决定某个方法
     * 2 方法的操作
     * methed.invoke
     */
    public void printMeth() {

        A a = new A();
        Class c = a.getClass();

        try {
            Method m = c.getMethod("print", new Class[]{int.class, int.class});
            //方法的反射
           Object obj =  m.invoke(a,new Object[]{10,20});
           //2

            Method m2 = c.getMethod("print",String.class,String.class);
            m2.invoke(a,"helo","wold");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public class A {
        public void print(int a, int b) {

        }

        public void print(String a, String b) {

        }
    }
}
