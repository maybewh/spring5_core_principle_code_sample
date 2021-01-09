package com.wanghui.design.pattern.singleton;

public class LazySimpleSingleton {

    private static LazySimpleSingleton simple = null;

    private LazySimpleSingleton(){}

    public static LazySimpleSingleton getInstance() {

        if (simple == null) {
            simple = new LazySimpleSingleton();
        }

        return simple;

    }
}
