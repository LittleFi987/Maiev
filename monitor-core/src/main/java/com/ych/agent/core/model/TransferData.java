package com.ych.agent.core.model;

import java.io.Serializable;

/**
 * Created by chenhao.ye on 27/01/2018.
 */
public class TransferData implements BaseData, Serializable {

    private static final long serialVersionUID = 8120924272686448867L;

    /**
     * global only call ID
     */
    private Long messageId;

    /**
     * span name
     * ex: XXX.doSomething
     * server.client
     */
    private String spanName;

    /**
     * parent spandId
     */
    private Long parentSpanId;

    /**
     * trace spanId
     */
    private Long spanId;

    private String packageName;

    private String methodName;

    private Long consumeTime;

    private Integer count;

    private Long maxTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public Long getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(Long parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public Long getSpanId() {
        return spanId;
    }

    public void setSpanId(Long spanId) {
        this.spanId = spanId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }
}
