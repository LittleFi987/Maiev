package com.ych.core.handler;

import com.ych.core.common.Pagination;
import com.ych.core.dto.ProjectDto;
import com.ych.core.model.MonitorProject;
import com.ych.core.model.MonitorProjectUrl;
import com.ych.core.service.ProjectService;
import com.ych.core.service.ProjectUrlService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> urls = projectDto.getUrls();
        for (String url : urls) {
            projectUrl.setMonitorUrl(url);
            projectUrl.setProjectId(project.getId());
            projectUrlService.create(projectUrl);
        }
    }

    public Pagination<MonitorProject> paging(Integer page, Integer size) {
        return projectService.list(page, size);
    }

    public List<MonitorProject> listAll() {
        return projectService.listAll();
    }

    @Transactional
    public void update(ProjectDto projectDto) {
        MonitorProject project = new MonitorProject();
        BeanUtils.copyProperties(projectDto, project);
        projectService.update(project);
        MonitorProjectUrl projectUrl = new MonitorProjectUrl();
        final Integer projectId = projectDto.getId();
        projectUrl.setProjectId(projectId);
        projectUrlService.deleteByProjectId(projectId);
        if (!CollectionUtils.isEmpty(projectDto.getUrls())) {
            List<String> urls = projectDto.getUrls();
            for (String url : urls) {
                projectUrl.setMonitorUrl(url);
                projectUrlService.create(projectUrl);
            }
        }
    }

    public ProjectDto getById(Integer id) {
        MonitorProject project = projectService.getById(id);
        List<String> urlList = projectUrlService.getByProjectId(id).stream().map(MonitorProjectUrl::getMonitorUrl).collect(Collectors.toList());
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project, projectDto);
        projectDto.setUrls(urlList);
        return projectDto;
    }


}
