package com.ych.core.service;

import com.ych.core.enums.DeleteStatus;
import com.ych.core.enums.summary.HandleStatus;
import com.ych.core.model.AlarmSet;

import java.util.List;

/**
 * Created by chenhao.ye on 22/03/2018.
 */
public interface AlarmSetService {

    List<AlarmSet> getByMonitorItemId(Integer itemId);

    List<AlarmSet> listByItemIdAndFlag(Integer itemId, DeleteStatus status);
}
