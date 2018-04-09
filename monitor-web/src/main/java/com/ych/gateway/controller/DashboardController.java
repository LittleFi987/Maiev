package com.ych.gateway.controller;

import com.google.common.collect.Maps;
import com.ych.core.common.Response;
import com.ych.core.dto.SortItemDTO;
import com.ych.core.handler.DashBoardHandler;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
@RestController
public class DashboardController {

    @Resource
    private DashBoardHandler dashBoardHandler;

    @GetMapping("/api/dashboard")
    public Response dashboard(@CurrentUser Integer userId) {
        Map<String, Object> returnMap = Maps.newHashMap();
        Map<String, Long> count = dashBoardHandler.count(userId);
        List<SortItemDTO> sortItemDtos = dashBoardHandler.sort();
        returnMap.put("count", count);
        returnMap.put("sort", sortItemDtos);

        return Response.success(returnMap);
    }


}
