package com.ych.monitor;

import javassist.CtClass;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public interface Collect {

    /**
     * 判断是否是采集目标
     *
     * @param className
     * @param classLoader
     * @param ctClass
     * @return
     */
    boolean isTarget(String className, ClassLoader classLoader, CtClass ctClass);


    /**
     * 更改字节码
     *
     * @param loader
     * @param className
     * @param classfileBuffer
     * @param ctClass
     * @return
     * @throws Exception
     */
    byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer,
                     CtClass ctClass) throws Exception;


}
