package com.ych.core.service;

import com.ych.core.model.MonitorProjectUrl;

import java.util.List;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
public interface ProjectUrlService {

    void create(MonitorProjectUrl monitorProjectUrl);

    List<MonitorProjectUrl> getByProjectId(Integer projectId);

    void update(MonitorProjectUrl monitorProjectUrl);

    void deleteByProjectId(Integer projectId);

}
