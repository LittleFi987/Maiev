package com.ych.monitor;

import javassist.*;

import java.io.IOException;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class AgentLoader {


    private String className;

    private ClassLoader classLoader;

    private CtClass ctClass;

    public static final String AGENT_NAME = "$agent";


    public AgentLoader(String className, ClassLoader classLoader, CtClass ctClass) {
        this.className = className;
        this.classLoader = classLoader;
        this.ctClass = ctClass;
    }


    public void updateMethod(CtMethod method, String newMethodBody) throws CannotCompileException {
        CtMethod ctMethod = method;
        String methodName = method.getName();
        CtMethod agentMethod = CtNewMethod.copy(ctMethod, methodName + AGENT_NAME, ctClass, null);
        ctClass.addMethod(agentMethod);
        method.setBody(newMethodBody);
    }

    public byte[] toBytecode() throws IOException, CannotCompileException {
        return ctClass.toBytecode();
    }
}
