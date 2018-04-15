package com.ych.core.service.impl;

import com.ych.core.mapper.MonitorGroupItemMapper;
import com.ych.core.mapper.MonitorGroupMapper;
import com.ych.core.model.MonitorGroup;
import com.ych.core.model.MonitorGroupExample;
import com.ych.core.model.MonitorGroupItem;
import com.ych.core.model.MonitorGroupItemExample;
import com.ych.core.service.MonitorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<MonitorGroupItem> getByGroupId(Integer groupId) {
        MonitorGroupItemExample example = new MonitorGroupItemExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        return monitorGroupItemMapper.selectByExample(example);
    }
}
