package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.core.dto.ProjectDto;
import com.ych.core.handler.ProjectHandler;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 16/03/2018.
 */
@RestController
public class ProjectController {

    @Resource
    private ProjectHandler projectHandler;

    @PostMapping("/api/project")
    public Response create(@RequestBody ProjectDto projectDto, @CurrentUser Integer userId) {
        projectDto.setUserId(userId);
        projectHandler.create(projectDto);
        return Response.success();
    }
}
