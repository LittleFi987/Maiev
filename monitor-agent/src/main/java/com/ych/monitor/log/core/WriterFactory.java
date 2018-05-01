package com.ych.monitor.log.core;

import com.ych.monitor.boot.AgentPackagePath;
import com.ych.monitor.conf.Config;
import com.ych.monitor.exception.AgentPackageNotFoundException;
import com.ych.monitor.log.SystemOutWriter;
import com.ych.monitor.util.StringUtils;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */

public class WriterFactory {
    public static IWriter getLogWriter() {
        if (AgentPackagePath.isPathFound()) {
            if (StringUtils.isEmpty(Config.Logging.DIR)) {
                try {
                    Config.Logging.DIR = AgentPackagePath.getPath() + "/logs";
                } catch (AgentPackageNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return FileWriter.get();
        } else {
            return SystemOutWriter.INSTANCE;
        }
    }
}
