package com.wanghui.design.pattern.delegate.mvc;

import com.wanghui.design.pattern.delegate.mvc.controllers.MemberController;
import com.wanghui.design.pattern.delegate.mvc.controllers.SystemController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 相当于项目经理的角色

/**
 * 此处省略了web.xml，用于理解思路即可
 */
public class DispatcherServlet extends HttpServlet {

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();
        String mid = request.getParameter("mid");

        if ("getMemberById".equals(uri)) {
            new MemberController().getMemberById(mid);
        } else if ("getOrderById".equals(uri)) {
            new SystemController().logout();
        } else {
            response.getWriter().write("404 Not Found!");
        }


    }

    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            doDispatch(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
