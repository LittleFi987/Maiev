package com.ych.monitor.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 监控项
 * Created by Stay on 2017/1/6  22:41.
 */
public class MonitorBean implements Serializable{
    private static final long serialVersionUID = 8194998659909639294L;
    private String monitorName;
    private AtomicLong maxRequestTime;
    private AtomicLong totalRequestTime;
    private String requestType;
    private String host;
    private Date date;
    private AtomicInteger requestCount;


    public MonitorBean() {
    }

    public MonitorBean(String monitorName, String requestType, Date date) {
        this.monitorName = monitorName;
        this.requestType = requestType;
        this.date = date;
    }

    public MonitorBean(String monitorName, AtomicLong maxRequestTime, AtomicLong totalRequestTime, String requestType, String host, Date date, AtomicInteger requestCount) {
        this.monitorName = monitorName;
        this.maxRequestTime = maxRequestTime;
        this.totalRequestTime = totalRequestTime;
        this.requestType = requestType;
        this.host = host;
        this.date = date;
        this.requestCount = requestCount;
    }

    public MonitorBean(String monitorName, String requestType, String host) {
        this.monitorName = monitorName;
        this.requestType = requestType;
        this.host = host;
    }

    public MonitorBean(String requestType, String host) {
        this.requestType = requestType;
        this.host = host;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public AtomicLong getMaxRequestTime() {
        return maxRequestTime;
    }

    public void setMaxRequestTime(AtomicLong maxRequestTime) {
        this.maxRequestTime = maxRequestTime;
    }

    public AtomicLong getTotalRequestTime() {
        return totalRequestTime;
    }

    public void setTotalRequestTime(AtomicLong totalRequestTime) {
        this.totalRequestTime = totalRequestTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AtomicInteger getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(AtomicInteger requestCount) {
        this.requestCount = requestCount;
    }


    public void updateBean(Long time){
        addRequestCount();
        addTotalRequestTime(time);
        compareMaxRequestTime(time);
    }

    /**
     * 请求数加1
     */
    public void addRequestCount() {
        requestCount.getAndIncrement();
    }

    /**
     * 增加指定时间
     *
     * @param time
     */
    public void addTotalRequestTime(Long time) {
        totalRequestTime.getAndAdd(time);
    }

    public void compareMaxRequestTime(Long time) {
        if (time > this.maxRequestTime.get()) {
            this.maxRequestTime.getAndSet(time);
        }
    }

    @Override
    public String toString() {
        return "MonitorBean{" +
                "monitorName='" + monitorName + '\'' +
                ", maxRequestTime=" + maxRequestTime +
                ", totalRequestTime=" + totalRequestTime +
                ", requestType='" + requestType + '\'' +
                ", host='" + host + '\'' +
                ", date=" + date +
                ", requestCount=" + requestCount +
                '}';
    }
}
