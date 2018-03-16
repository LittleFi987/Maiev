package com.ych.monitor.message.impl;

import com.ych.monitor.Monitor;
import com.ych.monitor.message.Point;

import java.net.InetAddress;

/**
 * Created by Stay on 2017/2/22  14:44.
 */
public class DefaultPoint implements Point {

    private String recordName;

    private String requestType;

    private long time;



    public DefaultPoint() {
        this.time = System.currentTimeMillis();
    }

    public DefaultPoint(String recordName,String requestType) {
        this.recordName = recordName;
        this.requestType = requestType;
        this.time = System.currentTimeMillis();
    }

    @Override
    public void addData(String name, String methodType) {
        this.recordName = name;
        this.requestType = methodType;
    }

    @Override
    public void complete() {
        this.time = System.currentTimeMillis() - this.time;
        Monitor.trackMonitor(recordName,requestType,getLocalIp(),time);
    }


    protected String getLocalIp() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        }catch (Exception e){
            //ignore
        }
        return null;
    }

}
