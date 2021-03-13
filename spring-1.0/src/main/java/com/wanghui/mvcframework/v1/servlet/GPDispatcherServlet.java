package com.wanghui.mvcframework.v1.servlet;

import com.wanghui.mvcframework.annotation.GPController;
import com.wanghui.mvcframework.annotation.GPRequestMapping;
import com.wanghui.mvcframework.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPDispatcherServlet extends HttpServlet {

    private Map<String, Object> mapping = new HashMap<String, Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispatch(req, resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        url = url.replace(context, "").replaceAll("/+", "/");
        if (!this.mapping.containsKey(url)) {
            resp.getWriter().write("404 NOT FOUND!");
            return;
        }
        Method method = (Method) mapping.get(url);
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()), new Object[]{req,resp,params.get("name")[0]});

    }

    @Override
    public void init(ServletConfig config) {
        InputStream in = null;
        try {
            Properties configContext = new Properties();
            in = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            configContext.load(in);
            String scanPackage = configContext.getProperty("scanPackage");
            doScanner(scanPackage);
            for (String className : mapping.keySet()) {
                if (!className.contains(".")) {
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(GPController.class)) {
                    try {
                        mapping.put(className, clazz.newInstance());
                        String baseUrl = "";
                        if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                            GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                            baseUrl = requestMapping.value();
                        }
                        Method[] methods = clazz.getMethods();
                        for (Method method : methods) {
                            if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                                continue;
                            }
                            GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                            String url = baseUrl + "/" + requestMapping.value().replaceFirst("/+", "");
                            mapping.put(url, method);
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value();
                    if ("".equals(beanName)) {
                        beanName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    mapping.put(beanName, instance);
                    for (Class<?> i : clazz.getInterfaces()) {
                        mapping.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描包下的类
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replace("\\.", "/"));

        File file = new File(url.getFile());

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!f.getName().endsWith(".class")) {
                    continue;
                }else {
                    String clazzName = scanPackage + "." + f.getName().replace(".class", "");
                    this.mapping.put(clazzName, null);
                }
            }
        }
    }


}
