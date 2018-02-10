package com.ych.agent.core.conf;

import com.ych.agent.core.logging.core.LogLevel;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public class Config {


    public static class Logging {

        public static String FILE_NAME = "monitor-api.log";

        public static String DIR = "";

        public static int MAX_FILE_SIZE = 300 * 1024 * 1024;

        public static LogLevel LEVEL = LogLevel.DEBUG;
    }

}
