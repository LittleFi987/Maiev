package com.ych.core.common;


import com.ych.core.enums.ResponseEnum;
import com.ych.core.exception.BizException;
import org.springframework.util.StringUtils;

/**
 * Created by yangsj on 2016/1/26.
 */
public interface Param {

    void check();

    default void checkNotNull(Object value, ResponseEnum status) {
        checkArgs(value != null, status);
    }

    default void checkNotEmpty(String value, ResponseEnum status) {
        checkArgs(StringUtils.isEmpty(value), status);
    }


    default void checkArgs(boolean success, ResponseEnum status) {
        if (!success) {
            throw new BizException(status);
        }
    }

}
