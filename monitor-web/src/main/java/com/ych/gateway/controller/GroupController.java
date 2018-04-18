package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.core.dto.GroupDTO;
import com.ych.core.dto.ProjectGroupDTO;
import com.ych.core.handler.GroupHandler;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhao.ye on 27/03/2018.
 */
@RestController
public class GroupController {

    @Resource
    private GroupHandler groupHandler;

    @GetMapping("/api/gorup")
    public Response listGroup(@CurrentUser Integer userId) {
        Map<Integer, List<ProjectGroupDTO>> map = groupHandler.ListGroupByUserId(userId);
        return Response.success(map);
    }


}
