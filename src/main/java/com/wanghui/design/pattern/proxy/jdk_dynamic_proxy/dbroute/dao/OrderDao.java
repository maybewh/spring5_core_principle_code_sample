package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.dao;

import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.dbroute.entry.Order;

public class OrderDao {

    public int insert(Order order) {
        System.out.println("插入数据库成功");
        return 1;
    }
}
