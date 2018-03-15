package com.ych.gateway.advice;

import com.ych.core.common.Response;
import com.ych.core.exception.BizException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenhao.ye on 14/03/2018.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({BizException.class})
    @ResponseBody
    public final Response handleBizException(BizException ex) {
        return Response.failed(ex.getStatus());
    }

}
