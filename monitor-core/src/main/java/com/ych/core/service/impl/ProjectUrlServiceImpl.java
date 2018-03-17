package com.ych.core.service.impl;

import com.ych.core.mapper.MonitorProjectUrlMapper;
import com.ych.core.model.MonitorProjectUrl;
import com.ych.core.service.ProjectUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
}
