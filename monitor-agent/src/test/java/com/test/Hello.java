package com.test;

import com.ych.monitor.collects.SpringControlCollect;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
@Controller
@RequestMapping("/api")
public class Hello {

    public Hello() {
    }

    @RequestMapping("/hello")
    public void sayHello(int name) {
        System.out.println(name + ": hello");
    }

    public static void main(String[] args) throws NotFoundException {
        // -javaagent:/Users/chenhaoye/IdeaProjects/monitor/monitor-agent/target/monitor.jar
        Hello hello = new Hello();
        hello.sayHello(1);
    }

}
