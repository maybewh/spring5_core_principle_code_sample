package com.wanghui.design.pattern.strategy.promotion;

public class CashbackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("促销返现，通过支付宝进行返现");
    }
}
