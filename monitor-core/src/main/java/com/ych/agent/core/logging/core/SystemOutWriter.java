package com.ych.agent.core.logging.core;

import java.io.PrintStream;

/**
 * Created by chenhao.ye on 08/02/2018.
 */
public enum SystemOutWriter implements IWriter {
    INSTANCE;

    public void write(String message) {
        PrintStream out = System.out;
        out.println(message);
    }
}
