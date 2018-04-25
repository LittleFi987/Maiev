package com.ych.core.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ych.core.dto.*;
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
import org.springframework.util.ObjectUtils;

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
        List<MonitorProject> monitorProjects = projectService.listByUserId(userId, DeleteStatus.NORMAL);
        if (CollectionUtils.isEmpty(monitorProjects)) {
            return Maps.newHashMap();
        }
        List<Integer> projectIdList = monitorProjects.stream().map(MonitorProject::getId).collect(Collectors.toList());
        List<MonitorGroup> monitorGroups = monitorGroupService.listByProjectIds(projectIdList);
        if (CollectionUtils.isEmpty(monitorGroups)) {
            return Maps.newHashMap();
        }

        ProjectGroupDTO projectGroupDTO = null;
        for (MonitorProject monitorProject : monitorProjects) {
            List<ProjectGroupDTO> projectGroupDTOList = Lists.newArrayList();
            projectGroupDTO = new ProjectGroupDTO();
            projectGroupDTO.setProjectName(monitorProject.getProjectName());
            projectGroupDTO.setProjectId(monitorProject.getId());
            List<GroupDTO> groupDTOS = Lists.newArrayList();
            List<MonitorGroup> monitorGroupList = monitorGroupService.listByProjectId(monitorProject.getId());
            if (!CollectionUtils.isEmpty(monitorGroupList)) {
                for (MonitorGroup monitorGroup : monitorGroupList) {
                    GroupDTO groupDTO = new GroupDTO();
                    groupDTO.setGroupName(monitorGroup.getGroupName());
                    groupDTO.setId(monitorGroup.getId());
                    List<GroupDTO.GroupItem> groupItemList = Lists.newArrayList();
                    List<MonitorGroupItem> monitorGroupItems = monitorGroupService.listByGroupId(monitorGroup.getId());
                    for (MonitorGroupItem monitorGroupItem : monitorGroupItems) {
                        MonitorItem item = monitorItemService.getById(monitorGroupItem.getMonitorItemId());
                        GroupDTO.GroupItem groupItem = new GroupDTO.GroupItem();
                        groupItem.setItemId(item.getId());
                        groupItem.setItemName(item.getMonitorItemName());
                        groupItemList.add(groupItem);
                    }
                    groupDTO.setItems(groupItemList);
                    groupDTOS.add(groupDTO);
                }
                projectGroupDTO.setGroupDTOList(groupDTOS);
                projectGroupDTOList.add(projectGroupDTO);
            } else {
                continue;
            }
            map.put(monitorProject.getId(), projectGroupDTOList);
        }

        return map;
    }

    public void createGroup(Integer projectId, List<Integer> itemIds, String groupName) {
        monitorGroupService.createGroup(projectId, groupName, itemIds);
    }

    public void updateGroup(Integer groupId, List<Integer> itemIds, String groupName) {
        monitorGroupService.update(groupId, itemIds, groupName);
    }


    public GroupDetailDTO getDetailByProjectIdAndGroupId(Integer projectId, Integer groupId) {
        List<GroupItemDTO> groupItemDTOList = listByProjectIdAndGroupId(projectId, groupId);
        GroupDetailDTO groupDetailDTO = new GroupDetailDTO();
        groupDetailDTO.setItemDTOList(groupItemDTOList);
        MonitorGroup group = monitorGroupService.getById(groupId);
        groupDetailDTO.setGroupName(group.getGroupName());
        groupDetailDTO.setGroupId(groupId);
        groupDetailDTO.setProjectName(projectService.getById(projectId).getProjectName());
        return groupDetailDTO;
    }

    public List<GroupItemDTO> listByProjectIdAndGroupId(Integer projectId, Integer groupId) {
        List<MonitorItemDTO> itemDTOList = listByProjectId(projectId);
        List<MonitorGroupItem> monitorGroupItems = null;
        List<GroupItemDTO> groupItemDTOList = Lists.newArrayList();
        GroupItemDTO item = null;
        if (!ObjectUtils.isEmpty(groupId)) {
            monitorGroupItems = monitorGroupService.listByGroupId(groupId);
        }
        if (!CollectionUtils.isEmpty(monitorGroupItems)) {
            for (MonitorItemDTO itemDTO : itemDTOList) {
                item = new GroupItemDTO();
                item.setItemId(itemDTO.getItemId());
                item.setItemName(itemDTO.getMonitorName());
                for (MonitorGroupItem monitorGroupItem : monitorGroupItems) {
                    if (monitorGroupItem.getMonitorItemId().equals(itemDTO.getItemId())) {
                        item.setStatus(1);
                    }
                }
                if (item.getStatus() == null) {
                    item.setStatus(0);
                }
                groupItemDTOList.add(item);
            }
        } else {
            for (MonitorItemDTO itemDTO : itemDTOList) {
                item = new GroupItemDTO();
                item.setItemId(itemDTO.getItemId());
                item.setItemName(itemDTO.getMonitorName());
                if (item.getStatus() == null) {
                    item.setStatus(0);
                }
                groupItemDTOList.add(item);
            }
        }
        return groupItemDTOList;
    }


    public void deleteGroupById(Integer groupId) {
        monitorGroupService.deleteByGroupId(groupId);
    }


    public List<AlarmItemDTO> listGroupByUserId(Integer userId) {
        List<MonitorProject> monitorProjects = projectService.listByUserId(userId, DeleteStatus.NORMAL);
        if (CollectionUtils.isEmpty(monitorProjects)) {
            return null;
        }
        List<Integer> projectIdList = monitorProjects.stream().map(MonitorProject::getId).collect(Collectors.toList());
        List<AlarmItemDTO> itemDTOS = Lists.newArrayList();
        for (Integer id : projectIdList) {
            List<MonitorGroup> monitorGroups = monitorGroupService.listByProjectId(id);
            if (!CollectionUtils.isEmpty(monitorGroups)) {
                for (MonitorGroup monitorGroup : monitorGroups) {
                    AlarmItemDTO itemDTO = new AlarmItemDTO();
                    itemDTO.setId(monitorGroup.getId());
                    itemDTO.setName(monitorGroup.getGroupName());
                    itemDTOS.add(itemDTO);
                }
            }
        }
        return itemDTOS;

    }

    private List<MonitorItemDTO> listByProjectId(Integer projectId) {
        List<MonitorItem> monitorItems = monitorItemService.listByProjectId(projectId);
        List<MonitorItemDTO> itemDTOList = Lists.newArrayList();
        MonitorItemDTO itemDTO;
        for (MonitorItem monitorItem : monitorItems) {
            itemDTO = new MonitorItemDTO();
            itemDTO.setItemId(monitorItem.getId());
            itemDTO.setMonitorName(monitorItem.getMonitorItemName());
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }



}
