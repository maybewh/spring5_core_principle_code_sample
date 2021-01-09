package com.wanghui.design.pattern.singleton;

/**
 *  TODO --------> 去查询内部类的初始化原理
 *  除了反射会破坏单例化，反序列化也会破坏单例。
 *  原因：
 *      一个单例对象创建好后，有时候需要将对象序列化后写入磁盘，下次使用时，再从磁盘读取对象并进行反序列化。
 *   然后将其转化为内存对象。反序列化后的对象会重新分配内存，即重新创建。这样就违背了单例模式的初衷。
 */
public class SimpleSingletonInnerClass {

    // 使用LazyInnerClassGeneral的时候，默认会先初始化内部类
    // 如果没有使用，则内部类是不加载的
    private SimpleSingletonInnerClass() {
        // 防止反射破坏单例 反射调用newInstance方法时，还是会调用构造方法来初始化生成一个实例
        if (LazyHolder.LAZY != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * 每一个关键字都不多余，static是为了使用单例的空间共享，final保证这个方法不会被重写
     * @return
     */
    public static final SimpleSingletonInnerClass getInstance() {
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {

        private final static SimpleSingletonInnerClass LAZY = new SimpleSingletonInnerClass();

    }

}
