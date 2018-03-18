package com.ych.core.handler;

import com.ych.core.model.MonitorProjectUrl;
import com.ych.core.service.ProjectUrlService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Component
public class MonitorUrlHandler {

    @Resource
    private ProjectUrlService projectUrlService;

    public List<MonitorProjectUrl> listAll() {
        return projectUrlService.listAll();
    }

}
