package com.ych;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by chenhao.ye on 16/03/2018.
 */
@ImportResource({"classpath*:spring/app-context-*.xml"})
@SpringBootConfiguration
@EnableAutoConfiguration
public class GatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(GatewayApplication.class)
                .web(true)
                .run(args);
    }

}