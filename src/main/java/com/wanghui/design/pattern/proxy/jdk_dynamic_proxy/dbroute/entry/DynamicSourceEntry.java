package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.entry;

public class DynamicSourceEntry {

    // 默认数据源
    public final static String DEFAULT_SOURCE = null;

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private DynamicSourceEntry() {}

    // 清空数据源,将当前的数据源清除掉
    public void clear() {
        threadLocal.remove();
    }

    public static String get() {
        return threadLocal.get();
    }

    // 还原当前切换的数据源
    public static void restore() {
        threadLocal.set(DEFAULT_SOURCE);
    }

    // 根据当前年份设置对应的数据源
    public static void set(Integer year) {
        threadLocal.set("DB_" + year);
    }

}
