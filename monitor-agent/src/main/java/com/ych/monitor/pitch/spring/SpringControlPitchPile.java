package com.ych.monitor.pitch.spring;

import com.ych.monitor.AgentLoader;
import com.ych.monitor.collects.api.TransformMaker;
import com.ych.monitor.collects.core.SpringControlMaker;
import com.ych.monitor.interceptor.MethodsAroundInterceptor;
import com.ych.monitor.pitch.AbstractPitchPile;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author chenhao.ych
 * @date 2018-09-15
 */
public class SpringControlPitchPile extends AbstractPitchPile {

    public SpringControlPitchPile(MethodsAroundInterceptor interceptor, String methodName, String returnTypeName) {
        super(interceptor, methodName, returnTypeName);
    }

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

            agentLoader.updateMethod(ctMethod, buildSrc());
        }
        return agentLoader.toBytecode();
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
