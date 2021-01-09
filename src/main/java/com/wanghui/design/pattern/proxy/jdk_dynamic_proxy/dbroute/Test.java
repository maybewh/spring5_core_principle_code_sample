package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute;

import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.entry.Order;
import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.proxy.OrderServiceDynamicProxy;
import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.service.IOrderService;
import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.service.OrderServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws ParseException {

        Order order = new Order();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse("2019/01/21");
        order.setCreateTime(date.getTime());

        IOrderService orderService = (IOrderService) new OrderServiceDynamicProxy().getInstance(new OrderServiceImpl());
        orderService.createOrder(order);

    }
}
