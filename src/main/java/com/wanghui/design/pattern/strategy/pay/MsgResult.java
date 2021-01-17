package com.wanghui.design.pattern.strategy.pay;

public class MsgResult {

    private int code;

    private String msg;

    private Object data;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "支付状态：[" + code + "]," + msg + ",交易详情：" + data;
    }
}
