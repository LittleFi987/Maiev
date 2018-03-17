package com.ych.core.service;

import com.ych.core.common.Pagination;
import com.ych.core.model.MonitorProject;

import java.util.List;

/**
 * Created by chenhao.ye on 16/03/2018.
 */
public interface ProjectService {

    MonitorProject create(MonitorProject monitorProject);

    Pagination<MonitorProject> list(Integer page, Integer size);

    List<MonitorProject> listAll();

    void update(MonitorProject monitorProject);

    MonitorProject getById(Integer id);
}
