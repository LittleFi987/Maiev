package com.ych.core.dto;

import com.ych.core.common.CommonResponseEnum;
import com.ych.core.common.Param;
import lombok.Data;

/**
 * Created by chenhao.ye on 15/03/2018.
 */
@Data
public class LoginDto implements Param {

    private String username;

    private String pwd;

    @Override
    public void check() {
        checkNotEmpty(username, CommonResponseEnum.PARAM_ERROR);
        checkNotEmpty(pwd, CommonResponseEnum.PARAM_ERROR);
    }
}
