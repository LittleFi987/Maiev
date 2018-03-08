package com.ych.agent.core.plugin.match;

/**
 * Created by chenhao.ye on 12/02/2018.
 */
public class NameMatch implements ClassMatch {
    private String className;

    private NameMatch(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public static NameMatch byName(String className) {
        return new NameMatch(className);
    }
}
