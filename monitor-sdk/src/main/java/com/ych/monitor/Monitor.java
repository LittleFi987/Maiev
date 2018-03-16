package com.ych.monitor;

import com.ych.monitor.bean.MonitorBean;
import com.ych.monitor.message.Point;
import com.ych.monitor.message.impl.DefaultPoint;
import com.ych.monitor.util.InterceptorUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhao.ye on 14/03/2018.
 */
public enum Monitor {
    INSTANCE;

    private static BusinessMonitor businessMonitor = BusinessMonitor.INSTANCE;


    /**
     * 埋点的创建
     *
     * @param methodName
     * @param requestType
     * @return
     */
    public static Point newPoint(String methodName, String requestType) {
        return new DefaultPoint(methodName, requestType);
    }

    public static Point newPoint() {
        return new DefaultPoint();
    }


    public static void trackMonitor(HttpServletRequest request, Object handler, long time) {
        trackMonitor(InterceptorUtils.getMethodName(handler), request.getMethod(), request.getRemoteHost(), time);

    }


    public static void trackMonitor(String completeName, String requestType, String remoteHost, Long time) {
        businessMonitor.recordMonitorData(completeName, time, requestType, remoteHost);
    }


    public static Map<String, MonitorBean> getMonitorData() {
        Map<String, MonitorBean> map = new HashMap<String, MonitorBean>();
        businessMonitor.getBusinessMonitorDataMap(map);
        return map;
    }

}
