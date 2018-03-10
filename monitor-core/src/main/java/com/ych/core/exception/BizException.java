package com.ych.core.exception;

import com.ych.core.enums.ResponseEnum;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
public class BizException extends RuntimeException {
    private ResponseEnum responseEnum;

    public BizException(ResponseEnum status) {
        super();
        this.responseEnum = status;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }
}
