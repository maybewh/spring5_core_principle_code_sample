package com.wanghui.design.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;


public class PromotionStrategySingletonFactory {


    /**
     * 一般可以按照一下方法来处理,但是随着促销策略（类）越来越多，那么代码会很繁琐，会出现
     * 很多的if-else，故可采用工厂模式，同时，此处要限制只有一个对象。
     * 所以采用单例模式结合工厂模式来完成改造
     */
    public PromotionStrategy general(String key) {
        if ("COUPON".equals(key)) {
            return new CouponStrategy();
        } else if ("CASHBACK".equals(key)) {
            return new CashbackStrategy();
        } else {
            return new EmptyStrategy();
        }
    }

    /**
     * 使用单例模式 + 工厂模式来完成改造
     */
    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP =
            new HashMap<>(4);

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK, new CashbackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON, new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.NON_PROMOTION, new EmptyStrategy());
    }

    public interface PromotionKey {
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
        String NON_PROMOTION = "NON_PROMOTION";
    }

    private PromotionStrategySingletonFactory() {}

    public static PromotionStrategy getInstance(String key) {
        PromotionStrategy strategy = PROMOTION_STRATEGY_MAP.get(key);
        return strategy == null ? PROMOTION_STRATEGY_MAP.get(PromotionKey.NON_PROMOTION)
                : strategy;
    }

}
