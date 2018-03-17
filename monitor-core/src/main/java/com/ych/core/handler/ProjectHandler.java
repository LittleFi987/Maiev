package com.ych.core.handler;

import com.ych.core.dto.ProjectDto;
import com.ych.core.model.MonitorProject;
import com.ych.core.model.MonitorProjectUrl;
import com.ych.core.service.ProjectService;
import com.ych.core.service.ProjectUrlService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
@Component
public class ProjectHandler {

    @Resource
    private ProjectService projectService;

    @Resource
    private ProjectUrlService projectUrlService;

    @Transactional
    public void create(ProjectDto projectDto) {
        MonitorProject monitorProject = new MonitorProject();
        BeanUtils.copyProperties(projectDto, monitorProject);
        MonitorProject project = projectService.create(monitorProject);
        MonitorProjectUrl projectUrl = new MonitorProjectUrl();
        projectUrl.setMonitorUrl(projectDto.getUrl());
        projectUrl.setProjectId(project.getId());
        projectUrlService.create(projectUrl);
    }

}
