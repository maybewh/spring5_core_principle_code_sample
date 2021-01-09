package com.wanghui.design.pattern.singleton;

public class Excutor implements Runnable {

    @Override
    public void run() {
        LazySimpleSingleton simpleSingleton = LazySimpleSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + simpleSingleton);
    }
}
