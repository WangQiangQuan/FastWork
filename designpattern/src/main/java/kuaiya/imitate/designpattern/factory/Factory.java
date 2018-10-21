package kuaiya.imitate.designpattern.factory;

/**
 * 定义一个创建对象的接口，让子类决定实例化那个对类
 */

public abstract class Factory {
    public abstract <T extends Product> T createProduct(Class<T> clz);
}
