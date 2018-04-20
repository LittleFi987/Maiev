package com.ych.core.handler;

import com.ych.core.common.Pagination;
import com.ych.core.dto.ProjectDTO;
import com.ych.core.enums.DeleteStatus;
import com.ych.core.enums.project.ProjectStatus;
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

    @Transactional(rollbackFor = Exception.class)
    public void create(ProjectDTO projectDto) {
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

    public List<MonitorProject> listAllByUserId(Integer userId) {
        List<MonitorProject> monitorProjects = projectService.listByUserId(userId, DeleteStatus.NORMAL);
        return monitorProjects;
    }

    @Transactional
    public void update(ProjectDTO projectDto) {
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

    public ProjectDTO getById(Integer id) {
        MonitorProject project = projectService.getById(id);
        List<String> urlList = projectUrlService.getByProjectId(id).stream().map(MonitorProjectUrl::getMonitorUrl).collect(Collectors.toList());
        ProjectDTO projectDto = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDto);
        projectDto.setUrls(urlList);
        return projectDto;
    }

    public void deleteById(Integer id) {
        MonitorProject project = new MonitorProject();
        project.setId(id);
        project.setDeleteFlag(DeleteStatus.DELETED.getStatus());
        projectService.update(project);
    }

    public void updateStatus(ProjectStatus status, Integer projectId) {
        MonitorProject project = new MonitorProject();
        project.setId(projectId);
        project.setProjectStatus(status.getValue());
        projectService.update(project);
    }


}
