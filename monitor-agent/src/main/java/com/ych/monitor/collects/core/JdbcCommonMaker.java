package com.ych.monitor.collects.core;

import com.ych.monitor.collects.api.TransformMaker;

/**
 * @Author yechenhao
 * @Date 25/04/2018
 */
public class JdbcCommonMaker implements TransformMaker {


    @Override
    public String begin() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("com.ych.monitor.collects.JdbcCommonCollect instance = com.ych.monitor.collects.JdbcCommonCollect.INSTANCE;");
        return stringBuilder.toString();
    }

    @Override
    public String end() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("result = instance.proxyConnection((java.sql.Connection)result);");
        return stringBuilder.toString();
    }

    @Override
    public String error() {
        return "";
    }
}
