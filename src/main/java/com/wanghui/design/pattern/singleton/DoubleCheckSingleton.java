package com.wanghui.design.pattern.singleton;

public class DoubleCheckSingleton {

    private static DoubleCheckSingleton singleton = null;

    private DoubleCheckSingleton() {}

    public static DoubleCheckSingleton getInstance() {

        if (singleton == null) {
            // 只会有少部分线程会去争抢这把锁，这部分线程是判断对象为null的线程。
            // 而如果在方法上加锁，则所有的调用该方法的线程都需要争抢锁。因为有大部分线程获取时，对象不会为空
            synchronized (DoubleCheckSingleton.class) {

                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                    //1. 分配内存
                    //2. 初始化对象
                    //3. 设置lazy指向刚分配的内存地址
                }

            }
        }

        return singleton;

    }
}
