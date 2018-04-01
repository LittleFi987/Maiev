package com.ych.core.handler;

import com.google.common.collect.Lists;
import com.ych.core.common.Pagination;
import com.ych.core.dto.MonitorItemDto;
import com.ych.core.enums.summary.HandleStatus;
import com.ych.core.model.MonitorDetail;
import com.ych.core.model.MonitorItem;
import com.ych.core.model.MonitorProject;
import com.ych.core.model.MonitorSummary;
import com.ych.core.service.MonitorDetailService;
import com.ych.core.service.MonitorItemService;
import com.ych.core.service.MonitorSummaryService;
import com.ych.core.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Component
public class ItemHandler {

    @Resource
    private MonitorSummaryService monitorSummaryService;

    @Resource
    private MonitorItemService monitorItemService;

    @Resource
    private ProjectService projectService;

    @Resource
    private MonitorDetailService monitorDetailService;

    @Transactional
    public void settleItem() {
        List<MonitorSummary> summaryList = monitorSummaryService.listByStatus(HandleStatus.NO_HANDLE);
        if (CollectionUtils.isEmpty(summaryList)) {
            return;
        }
        for (MonitorSummary monitorSummary : summaryList) {
            // 查找监控item是否存在
            MonitorItem item = monitorItemService.getByItemNameAndProjectId(monitorSummary.getMonitorName(), monitorSummary.getProjectId());
            if (item == null) {
                // 不存在则创建
                MonitorItem newItem = new MonitorItem();
                newItem.setMonitorItemName(monitorSummary.getMonitorName());
                newItem.setProjectId(monitorSummary.getProjectId());
                MonitorProject project = projectService.getById(monitorSummary.getProjectId());
                newItem.setProjectName(project.getProjectName());
                monitorItemService.create(newItem);
                monitorDetailService.create(setDetail(monitorSummary, newItem.getId()));
            } else {
                // 存在则汇总
                MonitorDetail monitorDetail = monitorDetailService.getByItemId(item.getId());
                Integer requestCount = monitorDetail.getRequestCount();
                Long requestTime = monitorDetail.getRequestTime();
                Integer newRequestCount = requestCount + monitorSummary.getRequestCount();
                Long newRequestTime = requestTime + monitorSummary.getTotalRequestTime();
                Long avgTime = newRequestTime / newRequestCount;
                monitorDetail.setRequestTime(newRequestTime);
                monitorDetail.setRequestCount(newRequestCount);
                monitorDetail.setAvgTime(avgTime);
                monitorDetailService.update(monitorDetail);
            }

        }
    }

    public Pagination<MonitorItemDto> paging(Integer page, Integer size) {
        Pagination<MonitorDetail> paging = monitorDetailService.paging(page, size);
        List<MonitorDetail> detailList = paging.getList();
        List<MonitorItemDto> dtoList = Lists.newArrayList();
        for (MonitorDetail detail : detailList) {
            MonitorItemDto itemDto = new MonitorItemDto();
            MonitorItem item = monitorItemService.getById(detail.getMonitorItemId());
            BeanUtils.copyProperties(detail, itemDto);
            itemDto.setProjectName(item.getProjectName());
            itemDto.setMonitorName(item.getMonitorItemName());
            dtoList.add(itemDto);
        }
        Pagination<MonitorItemDto> newPaging = new Pagination<>();
        newPaging.setCurrentPage(page);
        newPaging.setPageSize(size);
        newPaging.setList(dtoList);
        newPaging.setTotalCount(paging.getTotalCount());
        return newPaging;
    }


    private MonitorDetail setDetail(MonitorSummary summary, Integer itemId) {
        MonitorDetail detail = new MonitorDetail();
        detail.setAvgTime(summary.getTotalRequestTime() / summary.getRequestCount());
        detail.setMonitorItemId(itemId);
        detail.setIp(summary.getMonitorHost());
        detail.setRequestCount(summary.getRequestCount());
        detail.setRequestTime(summary.getTotalRequestTime());
        detail.setRequestType(summary.getRequestType());
        return detail;
    }

}
