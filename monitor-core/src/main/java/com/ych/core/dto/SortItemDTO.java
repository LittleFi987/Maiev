package com.ych.core.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhao.ye on 06/04/2018.
 */
@Data
@ToString
public class SortItemDTO implements Serializable {

    private static final long serialVersionUID = 5622823873668653780L;
    private Integer id;

    private String ip;

    private String requestType;

    private Integer requestCount;

    private Long requestTime;

    private Long avgTime;

    private Integer monitorItemId;

    private Date createTime;

    private Date updateTime;

    private String projectName;

    private String itemName;

}
