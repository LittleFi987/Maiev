package com.ych.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 17/04/2018
 */
@Data
public class ProjectGroupDTO implements Serializable {

    private static final long serialVersionUID = -6157859049007032999L;

    private Integer projectId;

    private String projectName;

    private List<GroupDTO> groupDTOList;

}
