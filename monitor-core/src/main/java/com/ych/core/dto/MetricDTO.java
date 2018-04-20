package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@Data
public class MetricDTO implements Serializable {

    private static final long serialVersionUID = 6746107225433628165L;
    private String itemName;

    private Long maxRequestTime;

    private Date date;

}
