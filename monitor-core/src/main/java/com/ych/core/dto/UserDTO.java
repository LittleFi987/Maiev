package com.ych.core.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
@Data
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 3666091150901084817L;
    private Long id;

    private String username;

    private String nickname;

    private String pwd;

    private String token;

    private Integer deleteFlag;

    private String email;

    private Date createTime;

    private Date updateTime;


    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.token;
    }


}
