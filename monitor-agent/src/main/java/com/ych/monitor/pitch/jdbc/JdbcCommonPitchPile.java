package com.ych.monitor.pitch.jdbc;

import com.ych.monitor.AgentLoader;
import com.ych.monitor.interceptor.MethodsAroundInterceptor;
import com.ych.monitor.pitch.AbstractPitchPile;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author chenhao.ych
 * @date 2018-09-26
 */
public class JdbcCommonPitchPile extends AbstractPitchPile {

    private static final String JDBC_NAME = "com.mysql.jdbc.NonRegisteringDriver";

    public JdbcCommonPitchPile(MethodsAroundInterceptor interceptor, String methodName, String returnTypeName) {
        super(interceptor, methodName, returnTypeName);
    }

    @Override
    public boolean isTarget(String className, ClassLoader classLoader, CtClass ctClass) {
        if (JDBC_NAME.equals(className)) {
            return true;
        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctClass) throws Exception {
        AgentLoader agentLoader = new AgentLoader(className, loader, ctClass);
        CtMethod connectMethod = ctClass.getMethod("connect", "(Ljava/lang/String;Ljava/util/Properties;)Ljava/sql/Connection;");

        agentLoader.updateMethod(connectMethod, buildSrc());

        return agentLoader.toBytecode();
    }
}
