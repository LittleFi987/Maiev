package com.ych.core.service.impl;

import com.ych.core.enums.summary.HandleStatus;
import com.ych.core.mapper.MonitorSummaryMapper;
import com.ych.core.model.MonitorSummary;
import com.ych.core.service.MonitorSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Service
public class MonitorSummaryServiceImpl implements MonitorSummaryService {

    @Resource
    private MonitorSummaryMapper monitorSummaryMapper;


    @Override
    public void insertMonitorListSummary(List<MonitorSummary> list) {
        for (MonitorSummary monitorSummary : list) {
            monitorSummary.setCreateTime(new Date());
            monitorSummary.setUpdateTime(new Date());
            monitorSummary.setHandleFlag(HandleStatus.NO_HANDLE.getValue());
            monitorSummaryMapper.insert(monitorSummary);
        }
    }
}
