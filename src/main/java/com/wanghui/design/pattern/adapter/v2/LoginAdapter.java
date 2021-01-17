package com.wanghui.design.pattern.adapter.v2;

import com.wanghui.design.pattern.adapter.ResultMsg;

public interface LoginAdapter {

    public boolean support(Object adapter);

    ResultMsg login(String id, Object adapter);

}
