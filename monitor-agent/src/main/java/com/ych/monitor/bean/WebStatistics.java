package com.ych.monitor.bean;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class WebStatistics extends Statistics {

    private String urlAddress;

    private String interfaceName;

    public WebStatistics(Statistics statistics) {
        super(statistics);
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public String toString() {
        return "WebStatistics{" +
                "begin=" + begin +
                ", end=" + end +
                ", urlAddress='" + urlAddress + '\'' +
                ", userTime=" + userTime +
                ", interfaceName='" + interfaceName + '\'' +
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
