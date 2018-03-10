package com.ych.core.enums;

/**
 * Created by chenhao.ye on 10/03/2018.
 */
public enum UserStatus {
    NORMAL(0),
    DELETE(-1);

    private int value;


    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
