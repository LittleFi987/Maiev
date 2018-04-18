package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yechenhao
 * @Date 18/04/2018
 */
@Data
public class GroupItemDTO implements Serializable {

    private static final long serialVersionUID = -1568463601935769605L;

    private Integer itemId;

    private String itemName;

    private Integer status;

}
