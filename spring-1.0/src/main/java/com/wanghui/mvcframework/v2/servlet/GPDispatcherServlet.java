package com.wanghui.mvcframework.v2.servlet;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.wanghui.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {

    // 声明全局变量
    // 保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();

    // 保存扫描的所有的类名
    private List<String> classNames = new ArrayList<String>();

    // IOC容器，暂不考虑线程安全
    private Map<String, Object> ioc = new HashMap<String, Object>();

    // 保存url和Method的对应关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();

        String mappingUrl = url.replace(contextPath, "").replaceAll("/+", "/");

        if (!this.handlerMapping.containsKey(mappingUrl)) {
            resp.getWriter().write("404 NOT FOUND!");
            return;
        }

        Method method = this.handlerMapping.get(url);
        //第一个参数：方法所在的实例
        //第二个参数：调用时所需要的实参
        Map<String,String[]> params = req.getParameterMap();
        //获取方法的形参列表
        Class<?> [] parameterTypes = method.getParameterTypes();
        //保存请求的url参数列表
        Map<String,String[]> parameterMap = req.getParameterMap();
        //保存赋值参数的位置
        Object [] paramValues = new Object[parameterTypes.length];
        //按根据参数位置动态赋值
        for (int i = 0; i < parameterTypes.length; i ++){
            Class parameterType = parameterTypes[i];
            if(parameterType == HttpServletRequest.class){
                paramValues[i] = req;
                continue;
            }else if(parameterType == HttpServletResponse.class){
                paramValues[i] = resp;
                continue;
            }else if(parameterType == String.class){

                //提取方法中加了注解的参数
                Annotation[] [] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length ; j ++) {
                    for(Annotation a : pa[i]){
                        if(a instanceof GPRequestParam){
                            String paramName = ((GPRequestParam) a).value();
                            if(!"".equals(paramName.trim())){
                                String value = Arrays.toString(parameterMap.get(paramName))
                                        .replaceAll("\\[|\\]","")
                                        .replaceAll("\\s",",");
                                paramValues[i] = value;
                            }
                        }
                    }
                }

            }
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1. 加载配置文件
        doLoadConfig(config.getInitParameter("contextLocationConfig"));

        //2. 扫描相关类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3. 初始化扫描到的类，并且将他们放入到IOC容器中
        doInstance();

        //4. 完成依赖注入
        doAutowired();

        //5. 初始化HandleMapping
        initHandlerMapping();

        System.out.println("GP Spring framework is init.");
    }

    private void initHandlerMapping() {

        if (ioc.isEmpty()) return;

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(GPController.class)) continue;

            String baseUrl = "";

            // 获取类上面的url
            if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            // 获取方法上的url
            Method[] methods = clazz.getMethods();
            for (Method method :
                    methods) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)) continue;

                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                String url = "/" + baseUrl + requestMapping.value();
                url = url.replaceAll("/+", "/");

                // 将method和url进行映射
                handlerMapping.put(url, method);
                System.out.println("Mapped: url--> " + url + "class name:"+ method.getDeclaringClass().getName() + "method name:" + method.getName());
            }

        }

    }

    /**
     * 遍历IOC中所有的对象，并从IOC容器中找到其依赖的对象，通过反射给其赋值
     */
    private void doAutowired() {
        if (ioc.isEmpty()) return;

        // 遍历IOC容器中的对象
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Object instance = entry.getValue();
            // 获取该类所有声明的字段，包括私有的字段，但是不包括继承的字段
            Field[] fields = instance.getClass().getDeclaredFields();
            if (fields.length == 0) continue;
            for (Field field : fields) {
                if (!field.isAnnotationPresent(GPAutowired.class)) continue;
                GPAutowired gpAutowired = field.getAnnotation(GPAutowired.class);
                String beanName = gpAutowired.value();
                if ("".equals(beanName.trim())) {
                    beanName = field.getType().getSimpleName();
                }

                field.setAccessible(true); // 能够访问私有属性
                try {
                    // 执行注入过程
                    field.set(entry, ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 创建实例，也就是说会创建多个类型的对象，实际上就是简单工厂模式
     */
    private void doInstance() {
        if (classNames.isEmpty()) return;

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(GPController.class)) {
                    Object instance = clazz.newInstance();
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                } else {
                    continue;
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    private String toLowerFirstCase(String simpleName) {
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }

    /**
     * 扫描某个包下的所有类
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getPath());

        for (File f :
                classPath.listFiles()) {
            if (f.isDirectory()) {
                doScanner(scanPackage + "." + f.getName());
            } else if (f.isFile()) {
                if (!f.getName().endsWith(".class")) {
                    continue;
                }
                classNames.add(scanPackage + "." + f.getName().replace(".class", ""));
            }
        }
    }

    /**
     * 加载配置文件
     * @param contextLocationConfig
     */
    private void doLoadConfig(String contextLocationConfig) {
        InputStream in = null;

        in = this.getClass().getClassLoader().getResourceAsStream(contextLocationConfig);

        try {
            contextConfig.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
