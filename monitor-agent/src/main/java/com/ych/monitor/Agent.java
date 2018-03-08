package com.ych.monitor;

import com.ych.agent.core.logging.api.ILog;
import com.ych.agent.core.logging.api.LogManager;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.isInterface;
import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.not;

/**
 * Created by chenhao.ye on 27/01/2018.
 */
public class Agent {

    public static final ILog logger = LogManager.getLogger(Agent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("<------------------agent load start------------------->");

//        final PluginFinder pluginFinder = new PluginFinder(null);


        new AgentBuilder.Default().type(not(isInterface()).and(not(isStatic()))).transform(new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
//                logger.info("typeDescription name: " + typeDescription.getTypeName());
                return builder
                        .method(ElementMatchers.any()) // 拦截任意方法
                        .intercept(MethodDelegation.withDefaultConfiguration().to(TimeInterceptor.class)); // 委托
            }


        }).with(new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                logger.debug("On Transformation class [{}].", typeDescription.getName());
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
                logger.error("Failed to enhance class [{}], cause: {} ", typeName, throwable);
            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        }).installOn(inst);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));
    }

}
