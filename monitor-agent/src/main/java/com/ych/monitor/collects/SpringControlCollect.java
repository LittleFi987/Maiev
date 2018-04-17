package com.ych.monitor.collects;

import com.ych.monitor.AbstractCollectors;
import com.ych.monitor.AgentLoader;
import com.ych.monitor.Collect;
import com.ych.monitor.bean.Statistics;
import com.ych.monitor.bean.WebStatistics;
import com.ych.monitor.collects.api.TransformMaker;
import com.ych.monitor.collects.core.SpringControlMaker;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class SpringControlCollect extends AbstractCollectors implements Collect {

    public static SpringControlCollect INSTANCE = new SpringControlCollect();


    @Override
    public boolean isTarget(String className, ClassLoader classLoader, CtClass ctClass) {
        try {
            for (Object o : ctClass.getAnnotations()) {
                if (o.toString().startsWith("@org.springframework.stereotype.Controller") ||
                        o.toString().startsWith("@org.springframework.web.bind.annotation.RestController")) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {

        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctClass) throws Exception {
        AgentLoader agentLoader = new AgentLoader(className, loader, ctClass);
        String classRequestUrl = getRequestMappingValue(ctClass);
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            String requestUrl;
            // 过滤插桩方法
            if (!verifyMethod(ctMethod)) {
                continue;
            }

            requestUrl = classRequestUrl + getRequestMappingValue(ctMethod);
            if (requestUrl == null) {
                continue;
            }
            AgentLoader.MthodSrcBuild build = new AgentLoader.MthodSrcBuild();
            TransformMaker transformMaker = new SpringControlMaker(className, ctMethod.getName(), requestUrl);
            build.setBeginSrc(transformMaker.begin());
            build.setEndSrc(transformMaker.end());
            build.setErrorSrc(transformMaker.error());

            agentLoader.updateMethod(ctMethod, build);
        }
        return agentLoader.toBytecode();
    }


    @Override
    public Statistics begin(String className, String method) {
        WebStatistics statistics = new WebStatistics(super.begin(className, method));
        statistics.setLogType("control");
        return statistics;
    }

    @Override
    public void end(Statistics statistics) {
        super.end(statistics);
    }

    @Override
    public void sendStatistics(Statistics statistics) {

    }


    private String getRequestMappingValue(CtMethod method) throws ClassNotFoundException {
        String val = "";
        for (Object o : method.getAnnotations()) {
           if (o.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping") ||
                   o.toString().startsWith("@org.springframework.web.bind.annotation.GetMapping") ||
                   o.toString().startsWith("@org.springframework.web.bind.annotation.PostMapping")) {
               val = val + getAnnotationValue("value", o.toString());
               return val == null ? "/" : val;
           }
        }
        return null;
    }

    private String getRequestMappingValue(CtClass ctClass) throws ClassNotFoundException {
        String val = "";
        for (Object o : ctClass.getAnnotations()) {
            if (o.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping") ||
                    o.toString().startsWith("@org.springframework.web.bind.annotation.GetMapping") ||
                    o.toString().startsWith("@org.springframework.web.bind.annotation.PostMapping")) {
                val = val + getAnnotationValue("value", o.toString());
                return val == null ? "/" : val;
            }
        }
        return null;
    }
}
