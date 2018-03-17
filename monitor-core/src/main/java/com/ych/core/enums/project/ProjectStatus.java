package com.ych.core.enums.project;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
public enum ProjectStatus {
    UNKNOW(-1, "未知"),
    NORMAL(0, "正常"),
    ERROR(-2, "异常");


    ProjectStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private int value;

    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
