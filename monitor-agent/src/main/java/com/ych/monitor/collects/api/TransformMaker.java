package com.ych.monitor.collects.api;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */
public interface TransformMaker {

    /**
     * 方法执行前代码块
     *
     * @return
     */
    String begin();

    /**
     * 方法执行后代码块
     *
     * @return
     */
    String end();

    /**
     * 方法执行错误代码块
     *
     * @return
     */
    String error();
}
