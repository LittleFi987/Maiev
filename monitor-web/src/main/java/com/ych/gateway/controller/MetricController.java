package com.ych.gateway.controller;

import com.ych.core.common.CommonResponseEnum;
import com.ych.core.common.Response;
import com.ych.core.dto.MetricDTO;
import com.ych.core.exception.BizException;
import com.ych.core.handler.MetricHandler;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@RestController
public class MetricController {

    @Resource
    private MetricHandler metricHandler;

    @GetMapping("/api/metric")
    public Response chart(@RequestParam("itemList") List<Integer> itemList) {
        if (CollectionUtils.isEmpty(itemList)) {
            throw new BizException(CommonResponseEnum.DATA_NULL);
        }
        List<MetricDTO> metric = metricHandler.getMetric(itemList);
        return Response.success(metric);
    }


}
