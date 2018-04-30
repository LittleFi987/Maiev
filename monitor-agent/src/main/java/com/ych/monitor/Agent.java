package com.ych.monitor;

import com.ych.monitor.collects.JdbcCommonCollect;
import com.ych.monitor.collects.SpringControlCollect;
import com.ych.monitor.collects.SpringServiceCollect;
import com.ych.monitor.log.ILog;
import com.ych.monitor.log.LogManager;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class Agent {

    private static Collect[] collects;

    public static final ILog log = LogManager.getLogger(Agent.class);

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        log.info("------------->Maiev Agent Start<------------");
        collects = new Collect[]{SpringControlCollect.INSTANCE,
                SpringServiceCollect.INSTANCE, JdbcCommonCollect.INSTANCE};
        instrumentation.addTransformer(new DefaultClassFileTransformer(collects));

    }

}
