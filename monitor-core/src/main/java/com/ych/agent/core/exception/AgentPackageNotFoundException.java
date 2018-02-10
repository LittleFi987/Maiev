package com.ych.agent.core.exception;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public class AgentPackageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1809217883679718681L;

    public AgentPackageNotFoundException() {
    }

    public AgentPackageNotFoundException(String message) {
        super(message);
    }
}
