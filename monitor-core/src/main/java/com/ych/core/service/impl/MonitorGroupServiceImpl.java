package com.ych.core.service.impl;

import com.ych.core.mapper.MonitorGroupItemMapper;
import com.ych.core.mapper.MonitorGroupMapper;
import com.ych.core.model.*;
import com.ych.core.service.MonitorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
@Service
public class MonitorGroupServiceImpl implements MonitorGroupService {

    @Resource
    private MonitorGroupMapper monitorGroupMapper;

    @Resource
    private MonitorGroupItemMapper monitorGroupItemMapper;


    @Override
    public Long countGroupByProjectId(List<Integer> projectId) {
        MonitorGroupExample example = new MonitorGroupExample();
        example.createCriteria().andProjectIdIn(projectId);
        return monitorGroupMapper.countByExample(example);
    }


    @Override
    @Transactional
    public void createGroup(Integer projectId, String name, List<Integer> itemIds) {
        MonitorGroup group = new MonitorGroup();
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());
        group.setGroupName(name);
        group.setProjectId(projectId);
        monitorGroupMapper.insertSelective(group);
        Integer groupId = group.getId();
        for (Integer itemId : itemIds) {
            MonitorGroupItem monitorGroupItem = new MonitorGroupItem();
            monitorGroupItem.setMonitorItemId(itemId);
            monitorGroupItem.setGroupId(groupId);
            monitorGroupItem.setCreateTime(new Date());
            monitorGroupItem.setUpdateTime(new Date());
            monitorGroupItemMapper.insertSelective(monitorGroupItem);
        }
    }

    @Override
    public List<MonitorGroup> listAll() {
        return monitorGroupMapper.selectByExample(new MonitorGroupExample());
    }

    @Override
    public List<MonitorGroupItem> listByGroupId(Integer groupId) {
        MonitorGroupItemExample example = new MonitorGroupItemExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        return monitorGroupItemMapper.selectByExample(example);
    }

    @Override
    public List<MonitorGroup> listByProjectIds(List<Integer> list) {
        MonitorGroupExample example = new MonitorGroupExample();
        example.createCriteria().andProjectIdIn(list);
        return monitorGroupMapper.selectByExample(example);
    }

    @Override
    public List<MonitorGroup> listByProjectId(Integer projectId) {
        MonitorGroupExample example = new MonitorGroupExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return monitorGroupMapper.selectByExample(example);
    }

    @Override
    public MonitorGroup getById(Integer id) {
        MonitorGroupExample example = new MonitorGroupExample();
        example.createCriteria().andIdEqualTo(id);
        List<MonitorGroup> monitorGroups = monitorGroupMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(monitorGroups)) {
            return null;
        }
        return monitorGroups.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Integer groupId, List<Integer> itemIds, String groupName) {
        MonitorGroup group = new MonitorGroup();
        group.setId(groupId);
        group.setUpdateTime(new Date());
        group.setGroupName(groupName);
        MonitorGroupItemExample example = new MonitorGroupItemExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        monitorGroupItemMapper.deleteByExample(example);
        for (Integer itemId : itemIds) {
            MonitorGroupItem monitorGroupItem = new MonitorGroupItem();
            monitorGroupItem.setMonitorItemId(itemId);
            monitorGroupItem.setGroupId(groupId);
            monitorGroupItem.setCreateTime(new Date());
            monitorGroupItem.setUpdateTime(new Date());
            monitorGroupItemMapper.insertSelective(monitorGroupItem);
        }
    }

    @Override
    public void deleteByGroupId(Integer groupId) {
        monitorGroupMapper.deleteByPrimaryKey(groupId);
    }
}
