package com.ych.monitor.log;

/**
 * @Author yechenhao
 * @Date 16/04/2018 2:24 PM
 */

public interface LogResolver {
    ILog getLogger(Class<?> clazz);
}
