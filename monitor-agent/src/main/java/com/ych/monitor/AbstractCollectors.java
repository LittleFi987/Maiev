package com.ych.monitor;

import com.ych.monitor.bean.Statistics;
import javassist.CtMethod;
import javassist.Modifier;

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
        System.out.println(statistics.toString());
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

    protected Boolean verifyMethod(CtMethod ctMethod) {
        // 屏蔽非公共方法
        if (!Modifier.isPublic(ctMethod.getModifiers())) {
            return false;
        }
        // 屏蔽静态方法
        if (Modifier.isNative(ctMethod.getModifiers())) {
            return false;
        }
        // 屏蔽本地方法
        if (Modifier.isStatic(ctMethod.getModifiers())) {
            return false;
        }
        return true;
    }

    protected static String getAnnotationValue(String key, String annotationDesc) {
        String regex = String.format("value=\\{\".*\"\\}");
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(annotationDesc);
        if (matcher.find()) {
            return matcher.group().substring(key.length() + 3, matcher.group().length() - 2);
        }
        return null;
    }

}
