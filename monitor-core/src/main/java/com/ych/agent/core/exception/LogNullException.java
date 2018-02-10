package com.ych.agent.core.exception;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public class LogNullException extends RuntimeException {

    private static final long serialVersionUID = -4358125676779581602L;

    public LogNullException() {
    }

    public LogNullException(String message) {
        super(message);
    }
}
