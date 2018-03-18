package com.ych.collector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.ych.core.handler.MonitorSummaryHandler;
import com.ych.core.handler.MonitorUrlHandler;
import com.ych.core.model.MonitorProjectUrl;
import com.ych.core.model.MonitorSummary;
import com.ych.core.service.ProjectUrlService;
import com.ych.monitor.bean.MonitorBean;
import com.ych.task.HttpMonitorTask;
import com.ych.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Component
@Slf4j
public class CollectJob {

    @Resource
    private MonitorUrlHandler monitorUrlHandler;

    @Resource
    private MonitorSummaryHandler summaryHandler;


    @Scheduled(fixedRate = 1000 * 30)
    public void collectData() {
        log.info("<-----------------[COLLECT-JOB] start--------------------->");
        List<MonitorProjectUrl> monitorProjectUrls = monitorUrlHandler.listAll();
        if (CollectionUtils.isEmpty(monitorProjectUrls)) {
            log.warn("[COLLECT-JOB] end ,monitor url is null");
            return;
        }
        for (MonitorProjectUrl monitorProjectUrl : monitorProjectUrls) {
            List<MonitorBean> monitorData = HttpClient.send(monitorProjectUrl.getMonitorUrl(), new TypeReference<List<MonitorBean>>() {});
            if (CollectionUtils.isEmpty(monitorData)) {
                log.warn("[COLLECT-JOB] monitor data is null");
                return;
            }
            List<MonitorSummary> summaryList = Lists.newArrayList();
            for (MonitorBean bean : monitorData) {
                MonitorSummary monitorSummary = new MonitorSummary();
                monitorSummary.setCreateDate(bean.getDate());
                monitorSummary.setMaxRequestTime(bean.getMaxRequestTime().longValue());
                monitorSummary.setMonitorHost(bean.getHost());
                monitorSummary.setMonitorName(bean.getMonitorName());
                monitorSummary.setProjectId(monitorProjectUrl.getProjectId());
                monitorSummary.setRequestCount(bean.getRequestCount().intValue());
                monitorSummary.setRequestType(bean.getRequestType());
                monitorSummary.setTotalRequestTime(bean.getTotalRequestTime().longValue());
                summaryList.add(monitorSummary);
            }
            summaryHandler.insert(summaryList);
        }
        log.info("<---------------[COLLECT-JOB] end---------------->");
    }

}
