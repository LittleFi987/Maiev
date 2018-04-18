package com.ych.monitor.collects.core;

import java.util.TreeMap;

/**
 * 链路追踪存储
 * @Author yechenhao
 * @Date 17/04/2018
 */
public enum  MonitorTrack {
    INSTANCE;

    public static final TreeMap<String, Long> treeMap = new TreeMap<>();

    public void put(String method, Long time) {
        treeMap.put(method, time);
    }


    public String get() {
        if (treeMap == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        treeMap.forEach((k, v) -> {
            stringBuilder.append(k + ":" + v + "--");
        });
        return stringBuilder.toString();
    }

    public void remove() {
        treeMap.clear();
    }

}
