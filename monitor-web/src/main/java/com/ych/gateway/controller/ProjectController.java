package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.core.dto.ProjectDTO;
import com.ych.core.handler.ProjectHandler;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 16/03/2018.
 */
@RestController
public class ProjectController {

    @Resource
    private ProjectHandler projectHandler;

    @PostMapping("/api/project")
    public Response create(@RequestBody ProjectDTO projectDto, @CurrentUser Integer userId) {
        projectDto.setUserId(userId);
        if (StringUtils.isEmpty(projectDto.getId())) {
            projectHandler.create(projectDto);
        } else {
            projectHandler.update(projectDto);
        }
        return Response.success();
    }

    @GetMapping("/api/project/paging")
    public Response paging(@RequestParam Integer page, @RequestParam Integer size) {
        return Response.success(projectHandler.paging(page, size));
    }

    @GetMapping("/api/project/list")
    public Response listAllByUserId(@CurrentUser Integer userId) {
        return Response.success(projectHandler.listAllByUserId(userId));
    }


    @GetMapping("/api/project")
    public Response getById(@RequestParam Integer id) {
        ProjectDTO projectDto = projectHandler.getById(id);
        return Response.success(projectDto);
    }

    @GetMapping("/api/delete-project")
    public Response deleteById(@RequestParam Integer id) {
        projectHandler.deleteById(id);
        return Response.success();
    }

}
