package com.wanghui.design.pattern.adapter.v2;

import com.wanghui.design.pattern.adapter.ResultMsg;

public class PassportForThirdAdapter extends SigninService implements IPassportForThird {

    public ResultMsg loginForQQ(String id) {
//        return processLogin(id,RegistForQQAdapter.class);
        return processLogin(id,LoginForQQAdapter.class);
    }

    public ResultMsg loginForWechat(String id) {
        return processLogin(id,LoginForWechatAdapter.class);
    }

    public ResultMsg loginForToken(String token) {
        return processLogin(token,LoginForTokenAdapter.class);
    }

    public ResultMsg loginForTelphone(String telphone, String code) {
        return processLogin(telphone,LoginForTelAdapter.class);
    }

    public ResultMsg loginForRegist(String username, String passport) {
        super.regist(username,passport);
        return super.login(username,passport);
    }

    // 加入了简单工厂模式和策略模式。其中策略是从几种登录模式中选取一种。简单工厂模式，根据传入进来的Class，动态生成对应的对象
    private ResultMsg processLogin(String id, Class<? extends LoginAdapter> adapter) {
        try {
            LoginAdapter loginAdapter = adapter.newInstance();

            //判断传过来的适配器是否能处理指定的逻辑
            if (loginAdapter.support(loginAdapter)) {
                return loginAdapter.login(id, loginAdapter);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }


}
