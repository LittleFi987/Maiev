package com.ych.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ych.monitor.bean.MonitorBean;
import com.ych.util.HttpClient;

import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
public class HttpMonitorTask implements Runnable {


    private String url;

    private Integer projectId;


    public HttpMonitorTask(String url, Integer projectId) {
        this.url = url;
        this.projectId = projectId;
    }

    @Override
    public void run() {
        List<MonitorBean> monitorData = HttpClient.send(url, new TypeReference<List<MonitorBean>>() {});


    }
}
