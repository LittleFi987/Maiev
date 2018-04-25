package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yechenhao
 * @Date 21/04/2018
 */
@Data
public class AlarmParam implements Serializable{

    private static final long serialVersionUID = -2623962290142910830L;
    private Integer timeThreshold;

    private Integer qpsThreshold;

    private Integer groupOrItemId;

    private Integer type;

}
