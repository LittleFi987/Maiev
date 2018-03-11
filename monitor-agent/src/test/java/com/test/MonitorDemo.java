package com.test;

import javassist.*;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class MonitorDemo {


    public static final String AGENT_NAME = "$agent";

    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        ClassPool pool = new ClassPool(true);
        String targetClassName = "com.test.Hello";

        CtClass targetClass = pool.get(targetClassName);

//        for (CtMethod method : targetClass.getDeclareMethods()) {
        CtMethod method = targetClass.getDeclaredMethod("sayHello");
        CtMethod agentMethod = CtNewMethod.copy(method, "sayHello$agent", targetClass, null);
            targetClass.addMethod(agentMethod);
            String src="{"
                    + "com.ych.monitor.Hi j = new com.ych.monitor.Hi();"
                    + "j.sayHi(\"hello\");"
                    + "long begin = System.nanoTime();"
                    + "sayHello$agent($$);"
                    + "long end = System.nanoTime();"
                    + "System.out.println(end-begin);"
                    + "}";
            method.setBody(src);
            targetClass.toClass();
//        }

        Hello hello = new Hello();
        hello.sayHello(1);


    }

}
