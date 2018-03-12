package com.ych.monitor.bean;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class ServiceStatistics extends Statistics {
    public String serviceName;

    public String methodName;

    public ServiceStatistics(Statistics s) {
        super(s);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
