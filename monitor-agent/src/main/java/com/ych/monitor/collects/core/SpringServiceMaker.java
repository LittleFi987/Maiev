package com.ych.monitor.collects.core;

import com.ych.monitor.collects.api.TransformMaker;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */
public class SpringServiceMaker implements TransformMaker {


    private String className;

    private String methodName;

    public SpringServiceMaker(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public String begin() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("com.ych.monitor.collects.SpringServiceCollect instance = com.ych.monitor.collects.SpringServiceCollect.INSTANCE;");
        sBuilder.append("com.ych.monitor.bean.ServiceStatistics statics = instance.begin(\"%s\",\"%s\");");
        return String.format(sBuilder.toString(), className, methodName);
    }

    @Override
    public String end() {
        return "instance.end(statics);";
    }

    @Override
    public String error() {
        return "instance.error(statics, e);";
    }
}
