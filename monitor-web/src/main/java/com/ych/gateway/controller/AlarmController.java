package com.ych.gateway.controller;

import com.ych.core.common.CommonResponseEnum;
import com.ych.core.common.Response;
import com.ych.core.dto.AlarmItemDTO;
import com.ych.core.dto.AlarmParam;
import com.ych.core.enums.DeleteStatus;
import com.ych.core.exception.BizException;
import com.ych.core.handler.GroupHandler;
import com.ych.core.handler.ItemHandler;
import com.ych.core.model.AlarmSet;
import com.ych.core.service.AlarmSetService;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@RestController
public class AlarmController {

    @Autowired
    private ItemHandler itemHandler;

    @Resource
    private GroupHandler groupHandler;

    @Resource
    private AlarmSetService alarmSetService;

    @GetMapping("/api/alarm/item")
    public Response getGroupOrItem(@RequestParam("type") Integer type, @CurrentUser Integer userId) {
        if (type.equals(1)) {
            return Response.success(groupHandler.listGroupByUserId(userId));
        }
        if (type.equals(2)) {
            List<AlarmItemDTO> itemDTOS = itemHandler.listAll(userId);
            return Response.success(itemDTOS);
        }
        throw new BizException(CommonResponseEnum.PARSE_ERROR);
    }

    @PostMapping("/api/alarm")
    public Response createAlarm(@RequestBody AlarmParam alarmParam, @CurrentUser Integer userId) {
        AlarmSet set = new AlarmSet();
        set.setQpsThreshold(alarmParam.getQpsThreshold());
        set.setTimeThreshold(alarmParam.getTimeThreshold());
        set.setUserId(userId);
        if (alarmParam.getType().equals(1)) {
            set.setGroupId(alarmParam.getGroupOrItemId());
        }
        if (alarmParam.getType().equals(2)) {
            set.setMonitorItemId(alarmParam.getGroupOrItemId());
        }
        alarmSetService.create(set);
        return Response.success();
    }

    @GetMapping("/api/alarm/list")
    public Response listByUserId(@CurrentUser Integer userId) {
        return Response.success(alarmSetService.listByUserId(userId, DeleteStatus.NORMAL));
    }

}
