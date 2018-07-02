package com.ych.monitor.bean;

import java.io.Serializable;

/**
 * @Author yechenhao
 * @Date 29/04/2018
 */
public class TraceContext implements Serializable {

    private static final long serialVersionUID = -5404182597326366899L;

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


    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getCs() {
        return cs;
    }

    public void setCs(Long cs) {
        this.cs = cs;
    }

    public Long getCr() {
        return cr;
    }

    public void setCr(Long cr) {
        this.cr = cr;
    }

    public Long getSs() {
        return ss;
    }

    public void setSs(Long ss) {
        this.ss = ss;
    }

    public Long getSr() {
        return sr;
    }

    public void setSr(Long sr) {
        this.sr = sr;
    }
}
