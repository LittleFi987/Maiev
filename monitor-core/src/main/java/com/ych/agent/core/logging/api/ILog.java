package com.ych.agent.core.logging.api;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public interface ILog {
    void info(String format);

    void info(String format, Object... arguments);

    void warn(String format, Object... arguments);

    void error(String format, Object... arguments);

    void error(String format, Throwable e);

    void error(Throwable e, String format, Object... arguments);

    boolean isDebugEnable();

    boolean isInfoEnable();

    boolean isWarnEnable();

    boolean isErrorEnable();

    void debug(String format);

    void debug(String format, Object... arguments);

    void error(String format);
}
