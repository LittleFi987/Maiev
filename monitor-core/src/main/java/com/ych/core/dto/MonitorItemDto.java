package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Data
public class MonitorItemDto implements Serializable {

    private static final long serialVersionUID = 3772667324850511746L;
    private Integer id;

    private String monitorName;

    private String projectName;

    private String requestType;

    private Integer requestCount;

    private String ip;

    private Long avgTime;

    private Integer itemId;

    private Date createTime;

    private Date updateTime;

}
