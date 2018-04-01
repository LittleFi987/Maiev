package com.ych.core.handler;

import com.ych.core.enums.DeleteStatus;
import com.ych.core.model.AlarmSet;
import com.ych.core.model.MonitorItem;
import com.ych.core.model.MonitorSummary;
import com.ych.core.service.AlarmSetService;
import com.ych.core.service.MonitorItemService;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhao.ye on 22/03/2018.
 */
@Component
public class AlarmHandler {

    @Resource
    private AlarmSetService alarmSetService;

    @Resource
    private MonitorItemService monitorItemService;

    public void alarm(MonitorSummary monitorSummary) {
        MonitorItem item = monitorItemService.getByItemName(monitorSummary.getMonitorName());
        Integer itemId = item.getId();
        List<AlarmSet> alarmSets = alarmSetService.listByItemIdAndFlag(itemId, DeleteStatus.NORMAL);
        if (CollectionUtils.isEmpty(alarmSets)) {
            return;
        }
        for (AlarmSet alarmSet : alarmSets) {
            if (alarmSet.getQpsThreshold() > monitorSummary.getRequestCount()) {
                //TODO 告警
            }

            if (alarmSet.getTimeThreshold() > (monitorSummary.getTotalRequestTime() / monitorSummary.getRequestCount())) {
                //TODO 告警
            }
        }
    }
}
