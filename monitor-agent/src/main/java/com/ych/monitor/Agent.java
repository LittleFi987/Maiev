package com.ych.monitor;

import com.ych.monitor.collects.SpringControlCollect;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class Agent {

    private static Collect collect;

    public static void premain(String agentArgs, Instrumentation instrumentation) {

        collect = SpringControlCollect.INSTANCE;
        instrumentation.addTransformer(new DefaultClassFileTransformer(collect));

    }

}
