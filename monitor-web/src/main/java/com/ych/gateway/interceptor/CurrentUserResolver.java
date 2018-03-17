package com.ych.gateway.interceptor;

import com.ych.core.enums.user.UserResponseEnum;
import com.ych.core.exception.BizException;
import com.ych.gateway.annotations.CurrentUser;
import com.ych.gateway.constant.HeaderDefineEnum;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by chenhao.ye on 17/03/2018.
 */
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (null != webRequest.getHeader(HeaderDefineEnum.USER_ID.getValue())) {
            String uid = webRequest.getHeader(HeaderDefineEnum.USER_ID.getValue()).split("--")[0];
            return Integer.parseInt(uid);
        } else {
            throw new BizException(UserResponseEnum.USER_NOT_FIND);
        }
    }
}
