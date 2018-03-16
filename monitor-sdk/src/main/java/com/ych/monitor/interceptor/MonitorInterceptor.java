package com.ych.monitor.interceptor;

import com.ych.monitor.Monitor;
import com.ych.monitor.message.Point;
import com.ych.monitor.util.InterceptorUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Stay on 2017/1/9  18:20.
 */
public class MonitorInterceptor implements HandlerInterceptor {
    private ThreadLocal<Point> threadLocal = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Point point = Monitor.newPoint();
        threadLocal.set(point);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Point point = threadLocal.get();
        point.addData(InterceptorUtils.getMethodName(handler), request.getMethod());
        point.complete();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
