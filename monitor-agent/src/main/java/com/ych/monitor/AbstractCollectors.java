package com.ych.monitor;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public abstract class AbstractCollectors {

    private final static ExecutorService threadService;

    private static final String localIp;

    private static long rejectedCount = 0;

    static {
        threadService = new ThreadPoolExecutor(20, 100, 20000L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(1000), (Runnable r, ThreadPoolExecutor executor) -> {
            rejectedCount ++;
            System.err.println();
        });
        localIp = "127.0.0.1";
    }



    public Statistics begin(String className, String method) {
        Statistics statistics = new Statistics();
        statistics.begin = System.currentTimeMillis();
        statistics.createTime = System.currentTimeMillis();
        return statistics;
    }

    public void end(Statistics statistics) {
        statistics.end = System.currentTimeMillis();
        statistics.userTime = statistics.getEnd() - statistics.getBegin();
        // 发送数据
    }

    public void error(Statistics statistics, Throwable throwable) {
        if (statistics != null) {
            statistics.errorMsg = throwable.getMessage();
            statistics.errorType = throwable.getClass().getName();
            if (throwable instanceof InvocationTargetException) {
                statistics.errorType = ((InvocationTargetException) throwable).getTargetException().getClass().getName();
                statistics.errorMsg = ((InvocationTargetException) throwable).getTargetException().getMessage();
            }
        }
        if (throwable != null) {
            // 发送错误日志给远程
            sendErrorStackByHttp("", throwable);
        }
    }

    private void sendErrorStackByHttp(String s, Throwable throwable) {
    }

    public abstract void sendStatistics(final Statistics statistics);

    protected void sendStatisticByHttp(final Statistics statistics, final String type) {
        // 发送监控数据
    }


    public static String getAnnotationValue(String key, String annotationDesc) {
        String regex = String.format("value=\\{\".*\"\\}");
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(annotationDesc);
        if (matcher.find()) {
            return matcher.group().substring(key.length() + 3, matcher.group().length());
        }
        return null;
    }


    public static class Statistics {
        public Long begin;

        public Long end;

        public Long userTime;

        public String errorMsg;

        public String errorType;

        public Long createTime;

        public String keyId;

        public String ip;

        public String logType;

        public Statistics() {
        }

        public Statistics(Statistics statistics) {
            this.setBegin(statistics.getBegin());
            this.setCreateTime(statistics.getCreateTime());
            this.setEnd(statistics.getEnd());
            this.setErrorMsg(statistics.getErrorMsg());
            this.setErrorType(statistics.getErrorType());
            this.setIp(statistics.getIp());
            this.setKeyId(statistics.getKeyId());
            this.setLogType(statistics.getLogType());
            this.setUserTime(statistics.getUserTime());
        }


        public Long getBegin() {
            return begin;
        }

        public void setBegin(Long begin) {
            this.begin = begin;
        }

        public Long getEnd() {
            return end;
        }

        public void setEnd(Long end) {
            this.end = end;
        }

        public Long getUserTime() {
            return userTime;
        }

        public void setUserTime(Long userTime) {
            this.userTime = userTime;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorType() {
            return errorType;
        }

        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getLogType() {
            return logType;
        }

        public void setLogType(String logType) {
            this.logType = logType;
        }
    }
}
