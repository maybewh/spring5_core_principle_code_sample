package com.wanghui.design.pattern.adapter.v2;


import com.wanghui.design.pattern.adapter.ResultMsg;

/**
 * Created by Tom.
 */
public class LoginForTokenAdapter implements LoginAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
