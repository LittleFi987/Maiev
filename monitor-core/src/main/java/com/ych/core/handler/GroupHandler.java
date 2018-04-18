package com.ych.core.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ych.core.dto.GroupDTO;
import com.ych.core.dto.ProjectGroupDTO;
import com.ych.core.enums.DeleteStatus;
import com.ych.core.model.MonitorGroup;
import com.ych.core.model.MonitorGroupItem;
import com.ych.core.model.MonitorItem;
import com.ych.core.model.MonitorProject;
import com.ych.core.service.MonitorGroupService;
import com.ych.core.service.MonitorItemService;
import com.ych.core.service.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chenhao.ye on 09/04/2018.
 */
@Component
public class GroupHandler {

    @Resource
    private ProjectService projectService;

    @Resource
    private MonitorGroupService monitorGroupService;

    @Resource
    private MonitorItemService monitorItemService;



    public Map<Integer, List<ProjectGroupDTO>> ListGroupByUserId(Integer userId) {
        Map<Integer, List<ProjectGroupDTO>> map = Maps.newHashMap();
        List<GroupDTO> groupDTOList = Lists.newArrayList();
        List<MonitorProject> monitorProjects = projectService.listByUserId(userId, DeleteStatus.NORMAL);
        if (CollectionUtils.isEmpty(monitorProjects)) {
            return Maps.newHashMap();
        }
        List<Integer> projectIdList = monitorProjects.stream().map(MonitorProject::getId).collect(Collectors.toList());
        List<MonitorGroup> monitorGroups = monitorGroupService.listByProjectIds(projectIdList);
        if (CollectionUtils.isEmpty(monitorGroups)) {
            return Maps.newHashMap();
        }
        GroupDTO groupDTO = null;
        for (MonitorGroup monitorGroup : monitorGroups) {
            if (map.get(monitorGroup.getProjectId()) == null) {
                map.put(monitorGroup.getProjectId(), Lists.newArrayList());
            }
            List<ProjectGroupDTO> projectGroupDTOList = map.get(monitorGroup.getProjectId());
            ProjectGroupDTO projectGroupDTO = new ProjectGroupDTO();
            groupDTO = new GroupDTO();
            groupDTO.setGroupName(monitorGroup.getGroupName());
            groupDTO.setId(monitorGroup.getId());
            List<MonitorGroupItem> monitorGroupItems = monitorGroupService.listByGroupId(monitorGroup.getId());
            List<GroupDTO.GroupItem> groupItemList = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(monitorGroupItems)) {
                for (MonitorGroupItem monitorGroupItem : monitorGroupItems) {
                    GroupDTO.GroupItem groupItem = new GroupDTO.GroupItem();
                    groupItem.setItemId(monitorGroupItem.getMonitorItemId());
                    MonitorItem item = monitorItemService.getById(monitorGroupItem.getMonitorItemId());
                    groupItem.setItemName(item.getMonitorItemName());
                    groupItemList.add(groupItem);
                }
            }
            groupDTO.setItems(groupItemList);
            groupDTOList.add(groupDTO);
            projectGroupDTO.setGroupDTOList(groupDTOList);
            projectGroupDTO.setProjectId(monitorGroup.getProjectId());
            projectGroupDTO.setProjectName(projectService.getById(monitorGroup.getProjectId()).getProjectName());
            projectGroupDTOList.add(projectGroupDTO);
        }
        return map;
    }

    public void createGroup(Integer projectId, List<Integer> itemIds, String groupName) {

    }

    public void updateGroup() {

    }

}
