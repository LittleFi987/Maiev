package com.test;

import javassist.NotFoundException;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
public class AgentHello {

    public static void main(String[] args) throws NotFoundException, InterruptedException {
        // -javaagent:/Users/chenhaoye/IdeaProjects/monitor/monitor-agent/target/monitor.jar
        System.out.println("-----------> main 加载");
        Hello hello = new Hello();
        hello.sayHello(1);
    }


}
