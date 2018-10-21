package kuaiya.imitate.designpattern.factory;

/**
 * 工厂模式
 * 定义一个用于创建对象的接口，让子类决定实例哪个类
 */

public class use {
    private void go() {
        Factory f = new ConcreteFactory();
        Product a = f.createProduct(ProductA.class);
        a.method();
    }
}
