package com.ych.core.enums.user;

import com.ych.core.enums.ResponseEnum;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
public enum UserResponseEnum implements ResponseEnum {
    USER_NOT_FIND("60000", "用户未找到"),
    USER_LOGIN_FAIL("60001", "用户登录失败");

    UserResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;


    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
