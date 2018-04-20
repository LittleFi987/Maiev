package com.ych.core.service.impl;

import com.google.common.collect.Lists;
import com.ych.core.enums.summary.HandleStatus;
import com.ych.core.mapper.MonitorSummaryMapper;
import com.ych.core.model.MonitorSummary;
import com.ych.core.model.MonitorSummaryExample;
import com.ych.core.service.MonitorSummaryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public List<MonitorSummary> listByStatus(HandleStatus status) {
        MonitorSummaryExample example = new MonitorSummaryExample();
        example.createCriteria().andHandleFlagEqualTo(status.getValue());
        List<MonitorSummary> summaryList = monitorSummaryMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(summaryList)) {
            return Lists.newArrayList();
        }
        return summaryList;
    }

    @Override
    public List<MonitorSummary> sortByMaxTotalRequestTime() {
        MonitorSummaryExample example = new MonitorSummaryExample();
        example.setOrderByClause("total_request_time desc limit 0,10");
        return monitorSummaryMapper.selectByExample(example);
    }

    @Override
    public List<MonitorSummary> listByMonitorName(String monitorName) {
        MonitorSummaryExample example = new MonitorSummaryExample();
        example.createCriteria().andMonitorNameEqualTo(monitorName);
        example.setOrderByClause("id desc limit 0,5");
        return monitorSummaryMapper.selectByExample(example);
    }

    @Override
    public void handleMonitorListSummary(List<MonitorSummary> list) {
        for (MonitorSummary monitorSummary : list) {
            monitorSummary.setHandleFlag(HandleStatus.HANDLE.getValue());
            monitorSummary.setUpdateTime(new Date());
            monitorSummaryMapper.updateByPrimaryKeySelective(monitorSummary);
        }
    }
}
