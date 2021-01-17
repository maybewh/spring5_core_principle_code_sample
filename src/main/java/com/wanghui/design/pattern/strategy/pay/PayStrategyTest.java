package com.wanghui.design.pattern.strategy.pay;

public class PayStrategyTest {

    public static void main(String[] args) {

        Order order = new Order("1", "20180311001000009", 324.45);

        //在支付的时候才决定使用哪类支付
        System.out.println(order.pay(PayStrategy.ALIPAY));

    }
}
