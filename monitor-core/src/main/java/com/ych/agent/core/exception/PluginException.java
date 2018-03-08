package com.ych.agent.core.exception;

/**
 * Created by chenhao.ye on 12/02/2018.
 */
public class PluginException extends RuntimeException {
    private static final long serialVersionUID = -3437278671865241954L;

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }
}
