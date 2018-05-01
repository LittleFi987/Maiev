package com.ych.monitor.log;

import com.ych.monitor.log.core.IWriter;

import java.io.PrintStream;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */
public enum  SystemOutWriter implements IWriter {
    INSTANCE;


    @Override
    public void write(String message) {
        PrintStream out = System.out;
        out.println(message);
    }
}
