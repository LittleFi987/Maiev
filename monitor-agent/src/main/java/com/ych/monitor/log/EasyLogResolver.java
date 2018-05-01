package com.ych.monitor.log;

import com.ych.monitor.log.core.EasyLogger;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */
public class EasyLogResolver implements LogResolver {

    @Override
    public ILog getLogger(Class<?> clazz) {
        return new EasyLogger(clazz);
    }
}
