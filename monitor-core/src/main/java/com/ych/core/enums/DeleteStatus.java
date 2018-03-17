package com.ych.core.enums;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
public enum DeleteStatus {
    NORMAL(0, "正常"),
    DELETED(-1, "删除");

    private int status;

    private String desc;


    DeleteStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
