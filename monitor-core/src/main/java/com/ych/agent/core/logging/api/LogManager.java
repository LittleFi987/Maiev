package com.ych.agent.core.logging.api;

import com.ych.agent.core.exception.LogNullException;
import com.ych.agent.core.logging.core.EasyLogResolver;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public class LogManager {
    private static LogResolver RESOLVER = new EasyLogResolver();

    public static ILog getLogger(Class<?> clazz) {
        if (RESOLVER == null) {
            throw new LogNullException();
        }
        return LogManager.RESOLVER.getLogger(clazz);
    }


}
