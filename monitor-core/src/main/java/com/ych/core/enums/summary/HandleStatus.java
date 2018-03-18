package com.ych.core.enums.summary;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
public enum HandleStatus {
    HANDLE(1, "已处理"),
    NO_HANDLE(0, "未处理");
    private int value;

    private String desc;

    HandleStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

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
