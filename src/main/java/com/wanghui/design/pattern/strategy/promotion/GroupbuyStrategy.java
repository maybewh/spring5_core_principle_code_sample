package com.wanghui.design.pattern.strategy.promotion;

public class GroupbuyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("拼团购买，全团享受团购价");
    }

}
