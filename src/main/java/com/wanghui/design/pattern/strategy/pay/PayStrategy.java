package com.wanghui.design.pattern.strategy.pay;

import java.util.HashMap;
import java.util.Map;

public class PayStrategy {

    public static final String ALIPAY = "ALIPAY";
    public static final String DEFAULT_KEY = "ALIPAY";
    public static final String WECHATPAY = "WECHATPAY";
    public static final String JDPAY = "JDPAY";
    public static final String UNIONPAY = "UNIONPAY";

    private static Map<String, Payment> PAY_STRATEGY_MAP = new HashMap<>();

    static {
        PAY_STRATEGY_MAP.put(ALIPAY, new Alipay());
        PAY_STRATEGY_MAP.put(DEFAULT_KEY, new Alipay());
        PAY_STRATEGY_MAP.put(WECHATPAY, new WeChatpay());
        PAY_STRATEGY_MAP.put(JDPAY, new JDPay());
        PAY_STRATEGY_MAP.put(UNIONPAY, new UnionPay());
    }

    public static Payment get(String key) {
        if (!PAY_STRATEGY_MAP.containsKey(key)) {
            return PAY_STRATEGY_MAP.get(DEFAULT_KEY);
        }
        return PAY_STRATEGY_MAP.get(key);
    }


}
