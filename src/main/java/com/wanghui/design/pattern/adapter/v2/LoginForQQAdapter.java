package com.wanghui.design.pattern.adapter.v2;


import com.wanghui.design.pattern.adapter.ResultMsg;

/**
 * Created by Tom on 2019/3/16.
 */
public class LoginForQQAdapter implements LoginAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
