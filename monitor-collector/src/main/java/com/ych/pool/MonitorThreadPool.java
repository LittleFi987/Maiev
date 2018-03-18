package com.ych.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
public enum  MonitorThreadPool {
    INSTANCE;

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<Runnable>());


    public void addTask(Runnable runnable) {
        executor.execute(runnable);
    }

}
