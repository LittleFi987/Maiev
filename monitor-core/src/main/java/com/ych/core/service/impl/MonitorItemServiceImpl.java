package com.ych.core.service.impl;

import com.ych.core.mapper.MonitorItemMapper;
import com.ych.core.model.MonitorItem;
import com.ych.core.model.MonitorItemExample;
import com.ych.core.service.MonitorItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Service
public class MonitorItemServiceImpl implements MonitorItemService {

    @Resource
    private MonitorItemMapper monitorItemMapper;


    @Override
    public void create(MonitorItem item) {
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        monitorItemMapper.insertSelective(item);
    }

    @Override
    public MonitorItem getByItemName(String itemName) {
        MonitorItemExample example = new MonitorItemExample();
        example.createCriteria().andMonitorItemNameEqualTo(itemName);
        List<MonitorItem> monitorItems = monitorItemMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(monitorItems)) {
            return null;
        }
        return monitorItems.get(0);
    }

    @Override
    public MonitorItem getByItemNameAndProjectId(String itemName, Integer projectId) {
        MonitorItemExample example = new MonitorItemExample();
        example.createCriteria().andMonitorItemNameEqualTo(itemName).andProjectIdEqualTo(projectId);
        List<MonitorItem> monitorItems = monitorItemMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(monitorItems)) {
            return null;
        }
        return monitorItems.get(0);
    }

    @Override
    public MonitorItem getById(Integer id) {
        MonitorItemExample example = new MonitorItemExample();
        example.createCriteria().andIdEqualTo(id);
        List<MonitorItem> monitorItems = monitorItemMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(monitorItems)) {
            return null;
        }
        return monitorItems.get(0);
    }

    @Override
    public Long countByProjectIds(List<Integer> projectId) {
        MonitorItemExample example = new MonitorItemExample();
        example.createCriteria().andProjectIdIn(projectId);
        return monitorItemMapper.countByExample(example);
    }

    @Override
    public List<MonitorItem> listByItemIds(List<Integer> itemIds) {
        MonitorItemExample example = new MonitorItemExample();
        example.createCriteria().andIdIn(itemIds);
        return monitorItemMapper.selectByExample(example);
    }

    @Override
    public List<MonitorItem> listByProjectId(Integer projectId) {
        MonitorItemExample example = new MonitorItemExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return monitorItemMapper.selectByExample(example);
    }
}
