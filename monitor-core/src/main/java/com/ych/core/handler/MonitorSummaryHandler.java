package com.ych.core.handler;

import com.ych.core.model.MonitorSummary;
import com.ych.core.service.MonitorSummaryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Component
public class MonitorSummaryHandler {

    @Resource
    private MonitorSummaryService monitorSummaryService;


    @Transactional
    public void insert(List<MonitorSummary> list) {
        monitorSummaryService.insertMonitorListSummary(list);
    }

}
