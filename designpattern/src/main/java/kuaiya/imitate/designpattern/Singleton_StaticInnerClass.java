package kuaiya.imitate.designpattern;

/**
 * Created by Administrator on 2016/6/26.
 * 静态内部类单例
 */
public class Singleton_StaticInnerClass {

    private Singleton_StaticInnerClass(){
    }

    private static class SingletonHolder{
        private static final Singleton_StaticInnerClass mInstance = new Singleton_StaticInnerClass();
    }
    public static Singleton_StaticInnerClass getInstance(){
        return SingletonHolder.mInstance;
    }


}
