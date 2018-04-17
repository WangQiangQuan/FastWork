package kuaiya.imitate.designpattern.proxy.s;

import java.lang.reflect.Proxy;

import kuaiya.imitate.designpattern.proxy.DynamicProxy;

/**
 *  由律师进行代理
 */

public class proClient {
    private void test(){
        XiaoMin m = new XiaoMin();
        Lawyer mlawyer = new Lawyer(m);
        mlawyer.submit();

    }

    private void testDynamic(){
        XiaoMin xiaomin = new XiaoMin();

        DynamicProxy proxy = new DynamicProxy(xiaomin);

        ClassLoader loader = xiaomin.getClass().getClassLoader();

        //动态构造代理律师
        ILawsuit mlawyer = (ILawsuit) Proxy.newProxyInstance(loader,new Class[]{ILawsuit.class},proxy);

        mlawyer.submit();
    }
    /**
     * https://www.cnblogs.com/fengmingyue/p/6092151.html
     * 3　newProxyInstance()方法的参数
     　　Proxy类的newInstance()方法有三个参数：
     　　　　ClassLoader loader：它是类加载器类型，你不用去理睬它，你只需要知道怎么可以获得它就可以了：MyInterface.class.getClassLoader()就可以获取到ClassLoader对象，没错，只要你有一个Class对象就可以获取到ClassLoader对象；
     　　　　Class[] interfaces：指定newProxyInstance()方法返回的对象要实现哪些接口，没错，可以指定多个接口，例如上面例子只我们只指定了一个接口：Class[] cs = {MyInterface.class};
     　　　　InvocationHandler h：它是最重要的一个参数！它是一个接口！它的名字叫调用处理器！你想一想，上面例子中mi对象是MyInterface接口的实现类对象，那么它一定是可以调用fun1()和fun2()方法了，难道你不想调用一下fun1()和fun2()方法么，它会执行些什么东东呢？其实无论你调用代理对象的什么方法，它都是在调用InvocationHandler的invoke()方法！
     */
}
