package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.service;

import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.dao.OrderDao;
import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.entry.Order;

public class OrderServiceImpl implements IOrderService {

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService调用OrderDao创建订单");
        return new OrderDao().insert(order);
    }

}
