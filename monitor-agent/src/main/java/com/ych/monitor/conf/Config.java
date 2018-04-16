package com.ych.monitor.conf;

import com.ych.monitor.log.core.LogLevel;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */

public class Config {

    public static class Logging {

        public static final LogLevel LEVEL = LogLevel.INFO;

        public static int MAX_FILE_SIZE = 300 * 1024 * 1024;

        public static String DIR = "";

        public static String FILE_NAME = "maiev-agent.log";
    }

}
