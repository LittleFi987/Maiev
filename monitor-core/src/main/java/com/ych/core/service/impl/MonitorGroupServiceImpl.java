package com.ych.core.service.impl;

import com.ych.core.mapper.MonitorGroupMapper;
import com.ych.core.model.MonitorGroupExample;
import com.ych.core.service.MonitorGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
@Service
public class MonitorGroupServiceImpl implements MonitorGroupService {

    @Resource
    private MonitorGroupMapper monitorGroupMapper;

    @Override
    public Long countGroupByProjectId(List<Integer> projectId) {
        MonitorGroupExample example = new MonitorGroupExample();
        example.createCriteria().andProjectIdIn(projectId);
        return monitorGroupMapper.countByExample(example);
    }
}
