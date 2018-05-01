package com.ych.monitor.log.core;

import com.ych.monitor.conf.Config;
import com.ych.monitor.conf.Constants;
import com.ych.monitor.log.ILog;
import com.ych.monitor.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */

public class EasyLogger implements ILog {

    private Class targetClass;

    public EasyLogger(Class targetClass) {
        this.targetClass = targetClass;
    }

    protected void logger(LogLevel level, String message, Throwable e) {
        WriterFactory.getLogWriter().write(format(level, message, e));
    }

    private String replaceParam(String message, Object... parameters) {
        StringBuilder stringBuilder = null;
        int paramentIndex = 0;
        while (message.indexOf("{}") > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(message.substring(0, message.indexOf("{}")));
            stringBuilder.append(parameters[paramentIndex]);
            stringBuilder.append(message.substring(message.indexOf("{}") + 2, message.length()));
            message = stringBuilder.toString();
            paramentIndex ++;
        }
        return message;
    }


    String format(LogLevel level, String message, Throwable t) {
        return StringUtils.join(' ', level.name(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                targetClass.getSimpleName(),
                ": ",
                message,
                t == null ? "" : format(t)
        );
    }

    String format(Throwable t) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        t.printStackTrace(new java.io.PrintWriter(buf, true));
        String expMessage = buf.toString();
        try {
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Constants.LINE_SEPARATOR + expMessage;
    }

    @Override
    public void info(String format) {
        if (isInfoEnable())
            logger(LogLevel.INFO, format, null);
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnable())
            logger(LogLevel.INFO, replaceParam(format, arguments), null);
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnable()) {
            logger(LogLevel.WARN, replaceParam(format, arguments), null);
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnable()) {
            logger(LogLevel.ERROR, replaceParam(format, arguments), null);
        }
    }

    @Override
    public void error(String format, Throwable e) {
        if (isErrorEnable())
            logger(LogLevel.ERROR, format, e);
    }

    @Override
    public void error(Throwable e, String format, Object... arguments) {
        if (isErrorEnable()) {
            logger(LogLevel.ERROR, replaceParam(format, arguments), e);
        }
    }

    @Override
    public boolean isDebugEnable() {
        return LogLevel.DEBUG.compareTo(Config.Logging.LEVEL) >= 0;
    }

    @Override
    public boolean isInfoEnable() {
        return LogLevel.INFO.compareTo(Config.Logging.LEVEL) >= 0;
    }

    @Override
    public boolean isWarnEnable() {
        return LogLevel.WARN.compareTo(Config.Logging.LEVEL) >= 0;
    }

    @Override
    public boolean isErrorEnable() {
        return LogLevel.ERROR.compareTo(Config.Logging.LEVEL) >= 0;
    }

    @Override
    public void debug(String format) {
        if (isDebugEnable()) {
            logger(LogLevel.DEBUG, format, null);
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (isDebugEnable()) {
            logger(LogLevel.DEBUG, replaceParam(format, arguments), null);
        }
    }

    @Override
    public void error(String format) {
        if (isErrorEnable()) {
            logger(LogLevel.ERROR, format, null);
        }
    }
}
