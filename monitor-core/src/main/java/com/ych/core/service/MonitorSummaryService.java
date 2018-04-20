package com.ych.core.service;

import com.ych.core.enums.summary.HandleStatus;
import com.ych.core.model.MonitorSummary;

import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
public interface MonitorSummaryService {

    void insertMonitorListSummary(List<MonitorSummary> list);

    List<MonitorSummary> listByStatus(HandleStatus status);

    List<MonitorSummary> sortByMaxTotalRequestTime();

    List<MonitorSummary> listByMonitorName(String monitorName);

    void handleMonitorListSummary(List<MonitorSummary> list);
}
