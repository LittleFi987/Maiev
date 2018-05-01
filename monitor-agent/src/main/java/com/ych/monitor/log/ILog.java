package com.ych.monitor.log;

/**
 * @Author yechenhao
 * @Date 16/04/2018 2:22 PM
 */

public interface ILog {

    void info(String format);

    void info(String format, Object... arguments);

    void warn(String format, Object... arguments);

    void error(String format, Throwable e);

    void error(Throwable e, String format, Object... arguments);

    boolean isDebugEnable();

    boolean isInfoEnable();

    boolean isWarnEnable();

    boolean isErrorEnable();

    void debug(String format);

    void debug(String format, Object... arguments);

    void error(String format);

    void error(String format, Object... arguments);

}
