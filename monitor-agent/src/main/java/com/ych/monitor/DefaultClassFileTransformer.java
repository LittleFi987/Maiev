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

/**
 * Created by chenhao.ye on 01/04/2018.
 */
public class DefaultClassFileTransformer  implements ClassFileTransformer {

    private Collect collect;

    private List<ClassLoader> classLoaderList = new ArrayList<>();

    public DefaultClassFileTransformer(Collect collect) {
        this.collect = collect;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassPool pool = ClassPool.getDefault();
//        if (classLoaderList.contains(loader)) {
//            return new byte[0];
//        } else {
//            classLoaderList.add(loader);
//        }
//        System.out.println(className);
//        System.out.println("loader---------->" + loader.getClass().getName() + "------------<");
        pool.appendClassPath(new LoaderClassPath(loader));
        try {
            CtClass ctClass = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
            if (collect.isTarget(className, loader, ctClass)) {
                return collect.transform(loader, className, classfileBuffer, ctClass);
            }
        } catch (Exception e) {
            // ignore
        }
        return new byte[0];
    }
}
