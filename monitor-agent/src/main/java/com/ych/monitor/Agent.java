package com.ych.monitor;

import com.ych.monitor.collects.SpringControlCollect;
import javassist.ClassPool;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class Agent implements ClassFileTransformer {

    private static Collect collect;


    public static void premain(String agentArgs, Instrumentation instrumentation) {

        collect = SpringControlCollect.INSTANCE;
        instrumentation.addTransformer(new Agent());

    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            String newClassName = className.replace("/", ".");
            System.out.println(newClassName);
            if (collect.isTarget(className, loader, new ClassPool(true).getCtClass(newClassName))) {
                return collect.transform(loader, className, classfileBuffer, new ClassPool(true).getCtClass(className));
            }
        } catch (Exception e) {
            // ignore
        }
//        System.out.println("className: " + newClassName);
        return new byte[0];
    }
}
