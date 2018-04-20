package com.ych.core.handler;

import com.google.common.collect.Lists;
import com.ych.core.dto.MetricDTO;
import com.ych.core.dto.MetricListDTO;
import com.ych.core.model.MonitorDetail;
import com.ych.core.model.MonitorItem;
import com.ych.core.service.MonitorDetailService;
import com.ych.core.service.MonitorItemService;
import com.ych.core.service.MonitorSummaryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@Component
public class MetricHandler {

    @Resource
    private MonitorItemService itemService;

    @Resource
    private MonitorDetailService monitorDetailService;

    public List<MetricDTO> getMetric(List<Integer> itemList) {
        List<MetricDTO> list = Lists.newArrayList();
        for (Integer itemId : itemList) {
            MetricDTO metricDTO = new MetricDTO();
            MonitorItem item = itemService.getById(itemId);
            MonitorDetail detail = monitorDetailService.getByItemId(itemId);
            metricDTO.setItemName(item.getMonitorItemName());
            metricDTO.setMaxRequestTime(detail.getAvgTime());
            list.add(metricDTO);
        }
        return list;
    }

}
