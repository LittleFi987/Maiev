package com.test;

import com.ych.monitor.collects.SpringServiceCollect;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class SpringServiceCollectTest {

    @Test
    public void isTargetTest() throws Exception {
        String name = "com.test.SpringServiceCollectTest$SpringServiceMock";
        ClassLoader loader = SpringServiceCollectTest.class.getClassLoader();
        ClassPool pool = new ClassPool(true);
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass ctClass = pool.get(name);
//        boolean target = SpringServiceCollect.INSTANCE.isTarget(name, loader, ctClass);
        SpringServiceCollect.INSTANCE.transform(loader, name, null, ctClass);
        SpringServiceMock mock = new SpringServiceMock();
        mock.sayHello("ych");
        Thread.sleep(2000);

    }


    @Service
    public static class SpringServiceMock {

        public void sayHello(String name) {
            System.out.println("hello " + name);
        }
    }

}
