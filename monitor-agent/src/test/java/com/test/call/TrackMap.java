package com.test.call;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */
public enum  TrackMap {
    INSTANCE;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void put(String method) {
        String localStr = threadLocal.get();
        localStr = localStr + " " + method + "-1";
        threadLocal.set(localStr);
    }

    public String getThreadLocal() {
        return threadLocal.get();
    }


}
