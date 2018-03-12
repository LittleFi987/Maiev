package com.test;

import com.ych.monitor.collects.SpringControlCollect;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class SpringControlCollectTest {


    @Test
    public void collectTarget() throws Exception {
        String name = "com.test.SpringControlCollectTest$Hello";
        ClassLoader loader = SpringServiceCollectTest.class.getClassLoader();
        ClassPool pool = new ClassPool(true);
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass ctClass = pool.get(name);
//        System.out.println(SpringControlCollect.INSTANCE.isTarget(name, loader, ctClass));
        SpringControlCollect.INSTANCE.transform(loader, name, null, ctClass);


        Hello hello = new Hello();
        hello.say();
    }


    @Controller
    @RequestMapping("/api")
    public static class Hello {
        @RequestMapping("/say")
        public void say() {
            System.out.println("hello");
        }
    }


}
