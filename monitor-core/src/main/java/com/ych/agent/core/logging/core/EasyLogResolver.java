package com.ych.agent.core.logging.core;

import com.ych.agent.core.logging.api.ILog;
import com.ych.agent.core.logging.api.LogResolver;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public class EasyLogResolver implements LogResolver {
    public ILog getLogger(Class<?> clazz) {
        return new EasyLogger(clazz);
    }
}
