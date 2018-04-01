package com.ych.core.service;

import com.ych.core.common.Pagination;
import com.ych.core.model.MonitorDetail;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
public interface MonitorDetailService {

    void create(MonitorDetail monitorDetail);

    void update(MonitorDetail monitorDetail);

    MonitorDetail getByItemId(Integer itemId);

    Pagination<MonitorDetail> paging(Integer page, Integer size);

}
