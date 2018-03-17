package com.ych.gateway.constant;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
public enum HeaderDefineEnum {
    USER_ID("X-Token");

    HeaderDefineEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
