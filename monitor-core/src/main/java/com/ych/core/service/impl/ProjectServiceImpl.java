package com.ych.core.service.impl;

import com.ych.core.enums.DeleteStatus;
import com.ych.core.enums.project.ProjectStatus;
import com.ych.core.mapper.MonitorProjectMapper;
import com.ych.core.model.MonitorProject;
import com.ych.core.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by chenhao.ye on 16/03/2018.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private MonitorProjectMapper monitorProjectMapper;

    @Override
    public MonitorProject create(MonitorProject monitorProject) {
        monitorProject.setCreateTime(new Date());
        monitorProject.setUpdateTime(new Date());
        if (ObjectUtils.isEmpty(monitorProject.getDeleteFlag())) {
            monitorProject.setDeleteFlag(DeleteStatus.NORMAL.getStatus());
            monitorProject.setProjectStatus(ProjectStatus.UNKNOW.getValue());
        }
        // 为了不让字段为空 随便赋值
        monitorProject.setToken("1");
        monitorProjectMapper.insert(monitorProject);
        Integer id = monitorProject.getId();
        String token = id + String.valueOf(new Date().getTime());
        monitorProject.setToken(token);
        monitorProjectMapper.updateByPrimaryKey(monitorProject);
        return monitorProject;
    }
}
