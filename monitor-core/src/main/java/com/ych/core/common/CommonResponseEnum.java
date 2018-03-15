package com.ych.core.common;

import com.ych.core.enums.ResponseEnum;

/**
 * Created by chenhao.ye on 14/03/2018.
 */
public enum CommonResponseEnum implements ResponseEnum {
    SUCCESS("0", "处理成功"),
    FAILED("1", "系统异常"),
    PARAM_ERROR("2", "参数检查失败"),
    RPC_ERROR("3", "外部接口调用失败"),
    PARSE_ERROR("4", "数据解析失败"),
    EXCEPTION("5", "系统异常"),
    DATA_NULL("6", "数据为空");
    private String code;
    private String message;

    CommonResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

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
