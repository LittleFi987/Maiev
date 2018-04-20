package com.ych.core.dto;

import com.ych.core.common.Param;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 19/04/2018
 */
@Data
public class GroupUpdateParam implements Param, Serializable {

    private static final long serialVersionUID = 2958244985467215283L;
    private Integer groupId;

    private List<Integer> itemIdList;

    private String groupName;
    @Override
    public void check() {

    }
}
