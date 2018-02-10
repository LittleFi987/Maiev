package com.ych.agent.core.logging.api;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public interface LogResolver {

    ILog getLogger(Class<?> clazz);
}
