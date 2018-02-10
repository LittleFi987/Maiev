package com.ych.monitor.component;

import com.google.common.base.Splitter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenhao.ye on 22/01/2018.
 */
@Data
@EqualsAndHashCode
public class Operation implements Serializable {
    private static final long serialVersionUID = -7167337731664545642L;

    private final int value;

    private final String text;

    private final List<String> operator;

    public Operation(int value) {
        this(value, null, null);
    }

    public Operation(int  value, String text, String operator) {
        this.value = value;
        this.text = text;
        this.operator = (operator == null || operator == "")?null: Splitter.on(",").splitToList(operator);
    }


}
