package com.ych.monitor.bean;

/**
 * @Author yechenhao
 * @Date 29/04/2018
 */
public abstract class AbstractContext {

    /**
     * 调用链唯一标识
     *
     */
    private String traceId;

    /**
     * 调用的节点id
     *
     */
    private String spanId;

    /**
     * 上一次调用的spanId  没有上级则为0
     *
     */
    private String parentId;

    /**
     * 客户端发送请求时间
     */
    private Long cs;

    /**
     * 客户端接收请求时间
     */
    private Long cr;

    /**
     * 服务端处理完请求 发送给下一次时间
     */
    private Long ss;

    /**
     * 服务端接收到请求的时间
     */
    private Long sr;



}
