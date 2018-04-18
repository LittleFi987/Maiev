package com.ych.core.dto;

import com.ych.core.common.Param;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yechenhao
 * @Date 18/04/2018
 */
@Data
public class GroupParam implements Param, Serializable {
    private static final long serialVersionUID = 7719418809947963564L;
    private Integer projectId;

    private List<Integer> itemIdList;

    private String groupName;

    @Override
    public void check() {

    }
}
