package com.ych.core.dto;

import com.ych.core.common.CommonResponseEnum;
import com.ych.core.common.Param;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 16/03/2018.
 */
@Data
@ToString
public class ProjectDto implements Serializable, Param {

    private static final long serialVersionUID = -3632277010712191571L;
    private Integer id;

    private String projectName;

    private Integer userId;

    private Integer projectStatus;

    private String token;

    private String note;

    private Integer deleteFlag;

    private List<String> urls;

    private Date createTime;

    private Date updateTime;

    @Override
    public void check() {
        checkNotEmpty(projectName, CommonResponseEnum.PARAM_ERROR);
        checkNotEmpty(urls, CommonResponseEnum.PARAM_ERROR);
    }
}
