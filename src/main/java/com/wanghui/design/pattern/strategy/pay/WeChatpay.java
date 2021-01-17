package com.wanghui.design.pattern.strategy.pay;

public class WeChatpay extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 245;
    }
}
