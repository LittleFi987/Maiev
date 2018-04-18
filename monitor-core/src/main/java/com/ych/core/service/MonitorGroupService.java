package com.ych.core.service;

import com.ych.core.model.MonitorGroup;
import com.ych.core.model.MonitorGroupItem;

import java.util.List;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
public interface MonitorGroupService {

    Long countGroupByProjectId(List<Integer> projectId);

    void createGroup(Integer projectId, String name, List<Integer> itemIds);

    List<MonitorGroup> listAll();

    List<MonitorGroupItem> listByGroupId(Integer groupId);

    List<MonitorGroup> listByProjectIds(List<Integer> list);
}
