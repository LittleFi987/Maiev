package com.ych.monitor;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
public class DefaultClassFileTransformer implements ClassFileTransformer {

    private Collect[] collects;

    private Map<ClassLoader, ClassPool> classPoolMap = new ConcurrentHashMap<>();

    public DefaultClassFileTransformer(Collect[] collects) {
        this.collects = collects;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null || loader == null
                || loader.getClass().getName().equals("sun.reflect.DelegatingClassLoader")
                || loader.getClass().getName().equals("org.apache.catalina.loader.standar")
                || className.indexOf("$Proxy") != -1
                || className.indexOf("CGLIB$") != -1
                || className.startsWith("java")
                || className.contains("transaction")
                || className.contains("autoconfigure")) {
            return null;
        }

        if (!classPoolMap.containsKey(loader)) {
            ClassPool pool = new ClassPool(true);
            pool.insertClassPath(new LoaderClassPath(loader));
            classPoolMap.put(loader, pool);
        }
        ClassPool classPool = classPoolMap.get(loader);
        try {
            className = className.replaceAll("/", ".");
            CtClass ctClass = classPool.get(className);
            for (int i = 0; i < collects.length; i++) {
                if (collects[i].isTarget(className, loader, ctClass)) {
                    byte[] bytes = collects[i].transform(loader, className, classfileBuffer, ctClass);
                    return bytes;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
