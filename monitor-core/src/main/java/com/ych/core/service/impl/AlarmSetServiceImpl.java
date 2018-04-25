package com.ych.core.service.impl;

import com.ych.core.enums.DeleteStatus;
import com.ych.core.enums.summary.HandleStatus;
import com.ych.core.mapper.AlarmSetMapper;
import com.ych.core.mapper.MonitorItemMapper;
import com.ych.core.model.AlarmSet;
import com.ych.core.model.AlarmSetExample;
import com.ych.core.model.MonitorItemExample;
import com.ych.core.service.AlarmSetService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 22/03/2018.
 */
@Service
public class AlarmSetServiceImpl implements AlarmSetService {

    @Resource
    private AlarmSetMapper alarmSetMapper;

    @Override
    public List<AlarmSet> getByMonitorItemId(Integer itemId) {
        AlarmSetExample alarmSetExample = new AlarmSetExample();
        alarmSetExample.createCriteria().andMonitorItemIdEqualTo(itemId);
        List<AlarmSet> alarmSets = alarmSetMapper.selectByExample(alarmSetExample);
        if (CollectionUtils.isEmpty(alarmSets)) {
            return null;
        }
        return alarmSets;
    }

    @Override
    public List<AlarmSet> listByItemIdAndFlag(Integer itemId, DeleteStatus status) {
        AlarmSetExample alarmSetExample = new AlarmSetExample();
        alarmSetExample.createCriteria().andMonitorItemIdEqualTo(itemId).andDeleteFlagEqualTo(status.getStatus());
        return alarmSetMapper.selectByExample(alarmSetExample);
    }

    @Override
    public void create(AlarmSet alarmSet) {
        alarmSet.setCreateTime(new Date());
        alarmSet.setUpdateTime(new Date());
        alarmSet.setDeleteFlag(DeleteStatus.NORMAL.getStatus());
        alarmSetMapper.insertSelective(alarmSet);
    }

    @Override
    public List<AlarmSet> listByUserId(Integer userId, DeleteStatus status) {
        AlarmSetExample example = new AlarmSetExample();
        example.createCriteria().andUserIdEqualTo(userId).andDeleteFlagEqualTo(status.getStatus());
        return alarmSetMapper.selectByExample(example);
    }
}
