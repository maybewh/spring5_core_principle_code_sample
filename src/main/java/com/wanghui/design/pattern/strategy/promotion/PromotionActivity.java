package com.wanghui.design.pattern.strategy.promotion;

public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy strategy) {
        this.promotionStrategy = strategy;
    }

    public void execute() {
        promotionStrategy.doPromotion();
    }

}
