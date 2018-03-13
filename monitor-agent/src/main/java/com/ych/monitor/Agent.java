package com.ych.monitor;

import com.ych.monitor.collects.SpringControlCollect;
import com.ych.monitor.collects.SpringServiceCollect;

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
//        collect.transform(loader, className, classfileBuffer, classBeingRedefined.getDeclaringClass());
        System.out.println("className: " + className);
        System.out.println("class: " + classBeingRedefined.getName());
        return new byte[0];
    }
}
