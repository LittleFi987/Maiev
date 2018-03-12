package com.ych.monitor.collects;

import com.ych.monitor.AbstractCollectors;
import com.ych.monitor.AgentLoader;
import com.ych.monitor.Collect;
import com.ych.monitor.bean.Statistics;
import com.ych.monitor.bean.WebStatistics;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class SpringControlCollect extends AbstractCollectors implements Collect {

    public static SpringControlCollect INSTANCE = new SpringControlCollect();

    private static String beginSrc;

    private static String endSrc;

    private static String errorSrc;

    static {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("com.ych.monitor.collects.SpringControlCollect instance = com.ych.monitor.collects.SpringControlCollect.INSTANCE;");
        sBuilder.append("com.ych.monitor.bean.WebStatistics statics = (com.ych.monitor.bean.WebStatistics)instance.begin(\"%s\",\"%s\");");
        sBuilder.append("statics.setUrlAddress(\"%s\");");
        beginSrc = sBuilder.toString();

        sBuilder = new StringBuilder();
        sBuilder.append("instance.end(statics);");
        endSrc = sBuilder.toString();

        sBuilder = new StringBuilder();
        sBuilder.append("instance.error(statics, e);");
        errorSrc = sBuilder.toString();
    }



    @Override
    public boolean isTarget(String className, ClassLoader classLoader, CtClass ctClass) {
        try {
            for (Object o : ctClass.getAnnotations()) {
                if (o.toString().startsWith("@org.springframework.stereotype.Controller")) {
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
        for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
            String requestUrl;
            // 过滤插桩方法
            if (!verifyMethod(ctMethod)) {
                continue;
            }

            requestUrl = getRequestMappingValue(ctMethod);
            if (requestUrl == null) {
                continue;
            }
            AgentLoader.MthodSrcBuild build = new AgentLoader.MthodSrcBuild();
            build.setBeginSrc(String.format(beginSrc, className, ctMethod.getName(), requestUrl));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);

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
        for (Object o : method.getAnnotations()) {
           if (o.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping")) {
               String val = getAnnotationValue("value", o.toString());
               return val == null ? "/" : val;
           }
        }
        return null;
    }
}
