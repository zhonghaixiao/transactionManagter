package com.example.demo.shiro.factory;


/**
 * 工厂方法设计模式，T表示工厂实现返回的实例类型
 * @param <T>
 */
public interface Factory<T> {

    /**
     * 返回T类型的实例。由工厂的实现决定每次调用时返回一个新的对象实例还是缓存的实例
     * @return
     */
    T getInstance();
}
