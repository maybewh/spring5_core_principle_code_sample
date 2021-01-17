package com.wanghui.design.pattern.strategy.pay;


public class Order {

    private String uid;

    private String orderId;

    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    //不需要写switch case，更不需要if-else
    public MsgResult pay() {
        return pay(PayStrategy.DEFAULT_KEY);
    }

    public MsgResult pay(String key) {
        Payment payment = PayStrategy.get(key);

        if (payment == null) {
            payment= PayStrategy.get(PayStrategy.DEFAULT_KEY);
        }

        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为：" + amount + "，开始扣款...");

        MsgResult msgResult = payment.pay(uid, amount);
        return msgResult;

    }
}
