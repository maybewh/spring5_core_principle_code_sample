package com.wanghui.design.pattern.strategy.pay;

public class JDPay extends Payment {
    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    protected double queryBalance(String uid) {
        return 125;
    }
}
