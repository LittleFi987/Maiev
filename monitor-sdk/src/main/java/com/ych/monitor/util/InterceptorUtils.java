package com.ych.monitor.util;

import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

/**
 * Created by Stay on 2017/2/22  17:25.
 */
public class InterceptorUtils {
    public static String getMethodName(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String completeName = method.getDeclaringClass().toString().replace("class","").trim() + "." + method.getName();
            return completeName;
        }
        return null;
    }
}
