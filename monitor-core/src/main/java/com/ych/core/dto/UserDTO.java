package com.ych.core.dto;

import com.ych.core.common.CommonResponseEnum;
import com.ych.core.common.Param;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
@Data
@ToString
public class UserDTO implements Serializable, Param {
    private static final long serialVersionUID = 1469170087592640655L;
    private Long id;

    private String username;

    private String nickname;

    private String pwd;

    private String token;

    private Integer deleteFlag;

    private String email;

    private Date createTime;

    private Date updateTime;


    @Override
    public void check() {
        checkNotEmpty(username, CommonResponseEnum.PARAM_ERROR);
        checkNotEmpty(nickname, CommonResponseEnum.PARAM_ERROR);
        checkNotEmpty(pwd, CommonResponseEnum.PARAM_ERROR);
        checkNotEmpty(email, CommonResponseEnum.PARAM_ERROR);
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.token;
    }


}
