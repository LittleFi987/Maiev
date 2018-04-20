package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.core.dto.*;
import com.ych.core.handler.GroupHandler;
import com.ych.core.handler.ProjectHandler;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private ProjectHandler projectHandler;

    @GetMapping("/api/group")
    public Response listGroup(@CurrentUser Integer userId) {
        Map<Integer, List<ProjectGroupDTO>> map = groupHandler.ListGroupByUserId(userId);
        return Response.success(map);
    }

    @PostMapping("/api/group")
    public Response createGroup(@RequestBody GroupParam groupParam) {
        groupHandler.createGroup(groupParam.getProjectId(), groupParam.getItemIdList(), groupParam.getGroupName());
        return Response.success();
    }

    @GetMapping("/api/group-project")
    public Response getItemByGroupIdAndProjectId(@RequestParam("projectId") Integer projectId,
                                                 @RequestParam(value = "groupId", required = false) Integer groupId) {
        List<GroupItemDTO> groupItemDTOList = groupHandler.listByProjectIdAndGroupId(projectId, groupId);
        return Response.success(groupItemDTOList);
    }


    @GetMapping("/api/group-detail")
    public Response getGroupDetail(@RequestParam("projectId") Integer projectId,
                                   @RequestParam("groupId") Integer groupId) {
        GroupDetailDTO detailDTO = groupHandler.getDetailByProjectIdAndGroupId(projectId, groupId);
        return Response.success(detailDTO);
    }

    @PostMapping("/api/update-group")
    public Response updateGroup(@RequestBody GroupUpdateParam groupUpdateParam) {
        groupHandler.updateGroup(groupUpdateParam.getGroupId(), groupUpdateParam.getItemIdList(), groupUpdateParam.getGroupName());
        return Response.success();
    }

    @GetMapping("/api/group/delete-id")
    public Response deleteByGroupId(@RequestParam("groupId") Integer groupId) {
        groupHandler.deleteGroupById(groupId);
        return Response.success();
    }

}
