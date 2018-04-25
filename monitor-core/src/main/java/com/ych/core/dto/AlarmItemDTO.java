package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yechenhao
 * @Date 21/04/2018
 */
@Data
public class AlarmItemDTO implements Serializable {

    private static final long serialVersionUID = 4865449315628114838L;

    private Integer id;

    private String name;
}
