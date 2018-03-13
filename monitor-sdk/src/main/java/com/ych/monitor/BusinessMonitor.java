package com.ych.monitor;


import com.ych.monitor.bean.MonitorBean;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Stay on 2017/1/16  0:06.
 */
public enum  BusinessMonitor {
    INSTANCE;

    private ConcurrentHashMap<String, MonitorBean> monitorMap = new ConcurrentHashMap<>();

    /**
     * 记录监控数据
     *
     * @param completeName
     * @param time
     * @param requestType
     * @param remoteHost
     */
    public void recordMonitorData(String completeName, Long time, String requestType, String remoteHost) {
        MonitorBean monitorBean = monitorMap.get(completeName);
        if (monitorBean == null) {
            monitorBean = new MonitorBean(completeName, new AtomicLong(time), new AtomicLong(time), requestType, remoteHost, new Date(), new AtomicInteger(0));
            MonitorBean originalBean = monitorMap.putIfAbsent(completeName, monitorBean);
            if (originalBean != null) {
                monitorBean = originalBean;
            }
        }
        monitorBean.updateBean(time);
    }

    /**
     * monitorMap输出
     *
     * @param map
     */
    public void getBusinessMonitorDataMap(Map<String, MonitorBean> map) {
        String countKey = null;
        for (Map.Entry<String, MonitorBean> entry : monitorMap.entrySet()) {
            countKey = entry.getKey();
            map.put(countKey, getAndRemove(countKey,entry.getValue()));
        }
    }

    /**
     * 根据k v 移除map中对应的数据 并返回移除前的value
     *
     * @param key
     * @param value
     * @return
     */
    private MonitorBean getAndRemove(String key, MonitorBean value) {
            while (!monitorMap.remove(key, value)) {
                value = monitorMap.get(key);
            }
            return value;
    }


}
