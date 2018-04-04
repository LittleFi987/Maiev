package com.ych.core.service;

import java.util.List;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
public interface MonitorGroupService {

    Long countGroupByProjectId(List<Integer> projectId);

}
