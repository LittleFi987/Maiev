package com.ych.core.service.impl;

import com.ych.core.mapper.AlarmRecordMapper;
import com.ych.core.model.AlarmRecordExample;
import com.ych.core.service.AlarmRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
@Service
public class AlarmRecordServiceImpl implements AlarmRecordService {

    @Resource
    private AlarmRecordMapper alarmRecordMapper;


    @Override
    public Long countAlarmRecord(Integer userId, Integer readFlag) {
        AlarmRecordExample example = new AlarmRecordExample();
        example.createCriteria().andUserIdEqualTo(userId).andReadFlagEqualTo(readFlag);
        return alarmRecordMapper.countByExample(example);
    }
}
