package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@Data
public class MetricListDTO implements Serializable {
    private static final long serialVersionUID = -7803950779426153258L;
    List<MetricDTO> metricDTOS;
}
