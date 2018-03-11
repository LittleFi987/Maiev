package com.ych.monitor;

import com.ych.monitor.collects.SpringServiceCollect;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class AgentMain implements ClassFileTransformer {

    private static Collect collect;


    public static void premain(String agentArgs, Instrumentation instrumentation) {
        if (agentArgs != null) {

        }

        collect = SpringServiceCollect.INSTANCE;

    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//        collect.transform(loader, className, classfileBuffer, );
        return new byte[0];
    }
}
