package com.ych.agent.core.logging.core;

import com.ych.agent.core.conf.Config;
import com.ych.agent.core.exception.AgentPackageNotFoundException;
import com.ych.agent.core.util.AgentPackagePath;
import com.ych.agent.core.util.StringUtils;


/**
 * Created by chenhao.ye on 07/02/2018.
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
