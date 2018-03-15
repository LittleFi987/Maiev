package com.ych.core.exception;

import com.ych.core.enums.ResponseEnum;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
public class BizException extends RuntimeException {
    private ResponseEnum status;

    public BizException(ResponseEnum status) {
        super();
        this.status = status;
    }

    public ResponseEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseEnum status) {
        this.status = status;
    }
}
