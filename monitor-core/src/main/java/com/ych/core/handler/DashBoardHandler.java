package com.ych.core.handler;

import com.google.common.collect.Maps;
import com.ych.core.service.AlarmRecordService;
import com.ych.core.service.MonitorGroupService;
import com.ych.core.service.MonitorItemService;
import com.ych.core.service.ProjectService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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


    public Map<String, Long> count(Integer userId) {
        HashMap<String, Long> returnMap = Maps.newHashMap();

        return returnMap;

    }


}
