package com.ych.monitor.pitch;

import javassist.CtClass;

/**
 * @author chenhao.ych
 * @date 2018-09-03
 */
public interface PitchPile {

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
