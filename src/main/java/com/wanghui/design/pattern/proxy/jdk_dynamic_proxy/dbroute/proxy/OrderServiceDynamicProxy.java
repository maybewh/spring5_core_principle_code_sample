package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.proxy;

import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.entry.DynamicSourceEntry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceDynamicProxy<T> implements InvocationHandler {

    private T target;

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    public T getInstance(T target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        /**
         * classLoader: 一个classloader对象，定义了由哪个classloader对象对生成的代理类进行加载
         * interfaces: 一个interface对象数组，表示我们将要给我们的代理对象提供一组什么样的接口，如果我们提供了这样一个接口对象数组，
         *              那么也就是声明了代理类实现了这些接口，代理类就可以调用接口中声明的所有方法。
         *             我的理解：传入需要被调用方法对应的接口 我的写法 (T) Proxy.newProxyInstance(classLoader, new Class[] {clazz}, this)
         * h：一个InvocationHandler对象，表示的是当动态代理对象调用方法的时候会关联到哪一个InvocationHandler对象上，并最终由其调用
         */
        return (T) Proxy.newProxyInstance(classLoader, clazz.getInterfaces(), this); //clazz.getInterfaces()-->返回该类实现的接口
    }

    /**
     *
     * @param proxy the proxy instance that the method was invoked on --> 调用该方法的代理实例
     * @param method
     * @param args an array of objects containing the values of the
     *      arguments passed in the method invocation on the proxy instance,
     *      or {@code null} if interface method takes no arguments.
     *      Arguments of primitive types are wrapped in instances of the
     *      appropriate primitive wrapper class, such as
     *      {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before(args[0]);
        Object object = method.invoke(target, args);
        after();
        return object;

    }

    private void after() {

        System.out.println("调用代理对象后");
    }

    /**
     *  在调用创建订单方法前，先根据时间选择对应的数据源
     * @param arg
     */
    private void before(Object arg) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("调用代理对象前");

        Long time = (Long) arg.getClass().getMethod("getCreateTime").invoke(arg);
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));

        System.out.println("代理类分配到数据源【DB_" + dbRouter + "】数据源处理数据");
        DynamicSourceEntry.set(dbRouter);

    }
}
