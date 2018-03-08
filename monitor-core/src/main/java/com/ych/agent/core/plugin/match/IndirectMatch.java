package com.ych.agent.core.plugin.match;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * Created by chenhao.ye on 12/02/2018.
 */
public interface IndirectMatch extends ClassMatch {
    ElementMatcher.Junction buildJunction();

    boolean isMatch(TypeDescription typeDescription);
}
