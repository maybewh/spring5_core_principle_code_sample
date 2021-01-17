package com.wanghui.design.pattern.adapter.v2;

import com.wanghui.design.pattern.adapter.Member;
import com.wanghui.design.pattern.adapter.ResultMsg;

public class SigninService {

    /**
     * 注册方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg regist(String username, String password){
        return  new ResultMsg(200,"注册成功",new Member());
    }


    /**
     * 登录的方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username,String password){
        return null;
    }
}
