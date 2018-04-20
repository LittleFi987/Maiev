package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@Data
public class GroupDetailDTO implements Serializable {

    private static final long serialVersionUID = -7287644102393537571L;

    private Integer groupId;

    private String projectName;

    private String groupName;

    private List<GroupItemDTO> itemDTOList;
}
