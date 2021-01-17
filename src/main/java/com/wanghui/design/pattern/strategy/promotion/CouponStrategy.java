package com.wanghui.design.pattern.strategy.promotion;

public class CouponStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("领取优惠券，课程价格直接减优惠券面值抵扣");
    }
}
