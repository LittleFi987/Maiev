package com.ych.monitor.pitch.spring;

import com.ych.monitor.AgentLoader;
import com.ych.monitor.interceptor.MethodsAroundInterceptor;
import com.ych.monitor.log.ILog;
import com.ych.monitor.log.LogManager;
import com.ych.monitor.pitch.AbstractPitchPile;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author chenhao.ych
 * @date 2018-09-26
 */
public class SpringServicePitchPile extends AbstractPitchPile {

    private ILog log = LogManager.getLogger(SpringServicePitchPile.class);

    public SpringServicePitchPile(MethodsAroundInterceptor interceptor, String methodName, String returnTypeName) {
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
            log.error(e.getMessage());
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

            agentLoader.updateMethod(ctMethod, buildSrc());
        }
        return agentLoader.toBytecode();
    }
}
