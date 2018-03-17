package com.ych.core.service.impl;

import com.google.common.collect.Lists;
import com.ych.core.mapper.MonitorProjectUrlMapper;
import com.ych.core.model.MonitorProjectUrl;
import com.ych.core.model.MonitorProjectUrlExample;
import com.ych.core.service.ProjectUrlService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
@Service
public class ProjectUrlServiceImpl implements ProjectUrlService {


    @Resource
    private MonitorProjectUrlMapper projectUrlMapper;


    @Override
    public void create(MonitorProjectUrl monitorProjectUrl) {
        monitorProjectUrl.setCreateTime(new Date());
        monitorProjectUrl.setUpdateTime(new Date());
        projectUrlMapper.insertSelective(monitorProjectUrl);
    }


    @Override
    public List<MonitorProjectUrl> getByProjectId(Integer projectId) {
        MonitorProjectUrlExample example = new MonitorProjectUrlExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        List<MonitorProjectUrl> monitorProjectUrlList = projectUrlMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(monitorProjectUrlList)) {
            return Lists.newArrayList();
        }
        return monitorProjectUrlList;
    }


    @Override
    public void update(MonitorProjectUrl monitorProjectUrl) {
        monitorProjectUrl.setUpdateTime(new Date());
        projectUrlMapper.updateByPrimaryKey(monitorProjectUrl);
    }


    @Override
    public void deleteByProjectId(Integer projectId) {
        MonitorProjectUrlExample example = new MonitorProjectUrlExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        projectUrlMapper.deleteByExample(example);
    }
}
