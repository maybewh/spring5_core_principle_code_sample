package com.wanghui.demo.service.impl;

import com.wanghui.demo.service.IDemoService;
import com.wanghui.mvcframework.annotation.GPService;

@GPService
public class DemoService implements IDemoService {

    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}
