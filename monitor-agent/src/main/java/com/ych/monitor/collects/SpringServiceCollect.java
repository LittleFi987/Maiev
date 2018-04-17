package com.ych.monitor.collects;

import com.ych.monitor.AbstractCollectors;
import com.ych.monitor.AgentLoader;
import com.ych.monitor.Collect;
import com.ych.monitor.bean.ServiceStatistics;
import com.ych.monitor.bean.Statistics;
import com.ych.monitor.collects.api.TransformMaker;
import com.ych.monitor.collects.core.SpringServiceMaker;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class SpringServiceCollect extends AbstractCollectors implements Collect {

    public static final SpringServiceCollect INSTANCE = new SpringServiceCollect();

    @Override
    public boolean isTarget(String className, ClassLoader classLoader, CtClass ctClass) {
        try {
            for (Object o : ctClass.getAnnotations()) {
                if (o.toString().startsWith("@org.springframework.stereotype.Service")) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctClass) throws Exception {
        AgentLoader agentLoader = new AgentLoader(className, loader, ctClass);
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            // 过滤插桩方法
            if (!verifyMethod(ctMethod)) {
                continue;
            }

            AgentLoader.MthodSrcBuild build = new AgentLoader.MthodSrcBuild();
            TransformMaker transformMaker = new SpringServiceMaker(className, ctMethod.getName());
            build.setBeginSrc(transformMaker.begin());
            build.setEndSrc(transformMaker.end());
            build.setErrorSrc(transformMaker.error());
            agentLoader.updateMethod(ctMethod, build);
        }
        return agentLoader.toBytecode();
    }

    @Override
    public Statistics begin(String className, String method) {
        ServiceStatistics statistics = new ServiceStatistics(super.begin(className, method));
        statistics.setServiceName(className);
        statistics.setMethodName(method);
        statistics.setLogType("service");
        return statistics;
    }


    @Override
    public void end(Statistics statistics) {
        super.end(statistics);
    }

    @Override
    public void sendStatistics(Statistics statistics) {

    }

}
