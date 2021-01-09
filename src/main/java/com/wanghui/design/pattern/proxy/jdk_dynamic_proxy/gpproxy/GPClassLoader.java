package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.gpproxy;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GPClassLoader extends ClassLoader {

    private File classPathFile;

    public GPClassLoader() {
        String classPath = GPClassLoader.class.getResource("").getPath();
        classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = GPClassLoader.class.getPackage().getName() + "." + name;

        if (classPathFile != null) {

            File classFile = new File(classPathFile,name.replaceAll("\\.","/") + ".class");

            if (classFile.exists()) {
                FileInputStream in = null;
                ByteOutputStream bos = null;
                try {
                    in = new FileInputStream(classFile);
                    bos = new ByteOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }

                    return defineClass(className, bos.getBytes(), 0, bos.size());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }

        return null;
    }
}
