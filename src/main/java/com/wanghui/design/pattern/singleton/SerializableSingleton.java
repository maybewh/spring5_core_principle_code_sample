package com.wanghui.design.pattern.singleton;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {

    private SerializableSingleton() {}

    private static final SerializableSingleton singleton = new SerializableSingleton();

    public static SerializableSingleton getInstance() {
        return singleton;
    }

    /**
     * 解决序列化后，反序列化不是同一对象
     * @return
     */
    private Object readResolve() {
        return singleton;
    }



}
