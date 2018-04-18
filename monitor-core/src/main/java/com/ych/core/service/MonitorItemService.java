package com.ych.core.service;

import com.ych.core.model.MonitorItem;

import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
public interface MonitorItemService {

    void create(MonitorItem item);

    MonitorItem getByItemName(String itemName);

    MonitorItem getByItemNameAndProjectId(String itemName, Integer projectId);

    MonitorItem getById(Integer id);

    Long countByProjectIds(List<Integer> projectId);

    List<MonitorItem> listByItemIds(List<Integer> itemIds);

}
