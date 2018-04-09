package com.ych.core.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ych.core.dto.SortItemDTO;
import com.ych.core.enums.DeleteStatus;
import com.ych.core.model.MonitorDetail;
import com.ych.core.model.MonitorItem;
import com.ych.core.model.MonitorProject;
import com.ych.core.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
@Component
public class DashBoardHandler {

    @Resource
    private ProjectService projectService;

    @Resource
    private MonitorItemService itemService;

    @Resource
    private MonitorGroupService monitorGroupService;

    @Resource
    private AlarmRecordService alarmRecordService;

    @Resource
    private MonitorDetailService monitorDetailService;


    public Map<String, Long> count(Integer userId) {
        HashMap<String, Long> returnMap = Maps.newHashMap();
        Long projectCount = projectService.countByUserId(userId, DeleteStatus.NORMAL);
        List<MonitorProject> monitorProjects = projectService.listByUserId(userId, DeleteStatus.NORMAL);
        Long itemCount = 0L;
        Long groupCount = 0L;
        if (!CollectionUtils.isEmpty(monitorProjects)) {
            List<Integer> projectIdList = monitorProjects.stream().map(MonitorProject::getId).collect(Collectors.toList());
            itemCount = itemService.countByProjectIds(projectIdList);
            groupCount = monitorGroupService.countGroupByProjectId(projectIdList);
        }
        Long alarmCount = alarmRecordService.countAlarmRecord(userId, DeleteStatus.NORMAL.getStatus());
        returnMap.put("projectCount", projectCount);
        returnMap.put("itemCount", itemCount);
        returnMap.put("groupCount", groupCount);
        returnMap.put("alarmCount", alarmCount);
        return returnMap;
    }

    public List<SortItemDTO> sort() {
        List<MonitorDetail> monitorDetails = monitorDetailService.sortByRequestTime();
        if (CollectionUtils.isEmpty(monitorDetails)) {
            return Lists.newArrayList();
        }
        List<SortItemDTO> sortItemDtoList = Lists.newArrayList();
        SortItemDTO sortItemDto = null;
        for (MonitorDetail monitorDetail : monitorDetails) {
            sortItemDto = new SortItemDTO();
            BeanUtils.copyProperties(monitorDetail, sortItemDto);
            MonitorItem item = itemService.getById(monitorDetail.getMonitorItemId());
            sortItemDto.setProjectName(item.getProjectName());
            sortItemDtoList.add(sortItemDto);
            sortItemDto.setItemName(item.getMonitorItemName());
        }
        return sortItemDtoList;
    }


}
