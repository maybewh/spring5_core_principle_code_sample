package com.wanghui.design.pattern.simple.factory;

/**
 *  通过反射创建对象的终极方法，Class泛型，传入的参数只能是ICourse的子类。
 *  这样入参进行了控制，也返回值也不需要强转
 */
public class SimpleFactoryByReflectionFinal {

    public ICourse create(Class<? extends ICourse> clazz) {
        try {

            return clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
