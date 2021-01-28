package com.wanghui.demo.mvc.action;

import com.wanghui.demo.service.IDemoService;
import com.wanghui.mvcframework.annotation.GPAutowired;
import com.wanghui.mvcframework.annotation.GPController;
import com.wanghui.mvcframework.annotation.GPRequestMapping;
import com.wanghui.mvcframework.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@GPController
@GPRequestMapping("/demo")
public class DemoAction {

    @GPAutowired
    private IDemoService demoService;

    public DemoAction(IDemoService demoService) {
        this.demoService = demoService;
    }

    @GPRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @GPRequestParam("name") String name){
        String result = demoService.get(name);
//		String result = "My name is " + name;
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @GPRequestParam("a") Integer a, @GPRequestParam("b") Integer b){
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/remove")
    public void remove(HttpServletRequest req,HttpServletResponse resp,
                       @GPRequestParam("id") Integer id){
    }

}

