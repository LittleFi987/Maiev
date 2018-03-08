package com.ych.agent.core.plugin.bytebuddy;

import net.bytebuddy.matcher.ElementMatcher;

/**
 * Created by chenhao.ye on 12/02/2018.
 */
public abstract class AbstractJunction<V> implements ElementMatcher.Junction<V> {
    @Override
    public <U extends V> Junction<U> and(ElementMatcher<? super U> other) {
        return new Conjunction<U>(this, other);
    }

    @Override
    public <U extends V> Junction<U> or(ElementMatcher<? super U> other) {
        return new Disjunction<U>(this, other);
    }
}
