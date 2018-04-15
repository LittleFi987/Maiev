package com.ych;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
public class AgentHello {

    public static void main(String[] args){
        // -javaagent:/Users/chenhaoye/IdeaProjects/monitor/monitor-agent/target/monitor.jar
        Hello hello = new Hello();
        hello.sayHello(1);
    }


}
