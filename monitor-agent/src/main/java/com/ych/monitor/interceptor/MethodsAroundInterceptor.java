package com.ych.monitor.interceptor;

/**
 * @author chenhao.ych
 * @date 2018-08-30
 */
public interface MethodsAroundInterceptor {

    String beforeMethod();

    String afterMethod();

    String handleMethodException();

}
