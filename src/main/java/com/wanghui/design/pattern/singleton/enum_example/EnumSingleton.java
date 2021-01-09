package com.wanghui.design.pattern.singleton.enum_example;

public enum EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData() {

        this.data = data;

    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

}
