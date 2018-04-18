package com.test.call;

import com.test.Hello;
import org.junit.Test;

/**
 * @Author yechenhao
 * @Date 16/04/2018
 */
public class TestTrack {

    @Test
    public void testTrack1() {
        new Thread(() -> {
            Hello hello = new Hello();
            hello.sayHello(1);
            testTrack2();
            testTrack3();
            String threadLocal = TrackMap.INSTANCE.getThreadLocal();
            System.out.println("thread1" + threadLocal);
        }).start();

        new Thread(() -> {
            Hello hello = new Hello();
            hello.sayHello(1);
            testTrack2();
            testTrack3();
            String threadLocal = TrackMap.INSTANCE.getThreadLocal();
            System.out.println("thread2" + threadLocal);
        }).start();


    }

    private void testTrack2() {
        TrackMap.INSTANCE.put("testTrack2");
    }

    public void testTrack3() {
        TrackMap.INSTANCE.put("testTrack3");
    }
}
