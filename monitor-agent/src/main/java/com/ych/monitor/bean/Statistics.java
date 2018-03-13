package com.ych.monitor.bean;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class Statistics {
    public Long begin;

    public Long end;

    public Long userTime;

    public String errorMsg;

    public String errorType;

    public Long createTime;

    public String keyId;

    public String ip;

    public String logType;

    public String methodName;

    public Statistics() {
    }

    public Statistics(Statistics statistics) {
        this.setBegin(statistics.getBegin());
        this.setCreateTime(statistics.getCreateTime());
        this.setEnd(statistics.getEnd());
        this.setErrorMsg(statistics.getErrorMsg());
        this.setErrorType(statistics.getErrorType());
        this.setIp(statistics.getIp());
        this.setKeyId(statistics.getKeyId());
        this.setLogType(statistics.getLogType());
        this.setUserTime(statistics.getUserTime());
        this.setMethodName(statistics.getMethodName());
    }



    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Long getUserTime() {
        return userTime;
    }

    public void setUserTime(Long userTime) {
        this.userTime = userTime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "begin=" + begin +
                ", end=" + end +
                ", userTime=" + userTime +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorType='" + errorType + '\'' +
                ", createTime=" + createTime +
                ", keyId='" + keyId + '\'' +
                ", ip='" + ip + '\'' +
                ", logType='" + logType + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
