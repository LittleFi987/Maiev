package com.ych.monitor.bean;

/**
 * @Author yechenhao
 * @Date 25/04/2018
 */
public interface BaseMonitor {

    /**
     * 全局追踪id 跟踪的入口点 根据需求来决定在哪生成traceId。
     * 比如一个http请求，首先入口是web应用，一般看完整的调用链这里自然是traceId生成的起点，结束点在web请求返回点。
     *
     * @return
     */
    String getTraceId();

    /**
     * 这是下一层的请求跟踪ID,这个也根据自己的需求，比如认为一次rpc，一次sql执行等都可以是一个span。一个traceId包含一个以上的spanId。
     *
     * @return
     */
    String getSpanId();

    /**
     * 上一次请求跟踪ID，用来将前后的请求串联起来。
     *
     * @return
     */
    String getParentId();

    /**
     * 客户端发起请求的时间，比如dubbo调用端开始执行远程调用之前。
     *
     * @return
     */
    Long getCs();

    /**
     * 客户端收到处理完请求的时间。
     *
     * @return
     */
    Long getCr();

    /**
     * 服务端处理完逻辑的时间。
     *
     * 客户端调用时间 = cr - ss
     *
     * @return
     */
    Long getSs();

    /**
     * 服务端收到调用端请求的时间。
     *
     * 服务端处理时间 = sr - ss
     *
     * @return
     */
    Long getSr();

}
