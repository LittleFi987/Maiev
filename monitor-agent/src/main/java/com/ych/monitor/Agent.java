package com.ych.monitor;

import com.ych.agent.core.logging.api.ILog;
import com.ych.agent.core.logging.api.LogManager;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.ModifierMatcher;
import net.bytebuddy.matcher.NegatingMatcher;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * Created by chenhao.ye on 27/01/2018.
 */
public class Agent {

    public static final ILog logger = LogManager.getLogger(Agent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("<------------------agent load start------------------->");
        new AgentBuilder.Default().type(new NegatingMatcher(new ModifierMatcher(ModifierMatcher.Mode.INTERFACE))).transform(new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                return builder
                        .method(ElementMatchers.isMethod()) // 拦截任意方法
                        .intercept(MethodDelegation.withDefaultConfiguration().to(TimeInterceptor.class)); // 委托
            }


        }).with(new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                if (logger.isDebugEnable()) {
                    logger.debug("On Transformation class {}.", typeDescription.getName());
                }
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
                logger.error("Failed to enhance class " + typeName, throwable);
            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        }).installOn(inst);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));

    }
}
