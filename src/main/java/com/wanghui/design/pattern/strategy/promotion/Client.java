package com.wanghui.design.pattern.strategy.promotion;

public class Client {

    public static void main(String[] args) {
        String strategy = "GRPOUPBUY";
        PromotionActivity activity = new PromotionActivity(
                PromotionStrategySingletonFactory.getInstance(strategy));
        activity.execute();

    }

}
