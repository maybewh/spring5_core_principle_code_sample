package com.wanghui.design.pattern.strategy.promotion;

public class EmptyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("没有优惠活动");
    }
}
