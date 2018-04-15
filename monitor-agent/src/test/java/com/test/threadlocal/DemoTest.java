package com.test.threadlocal;

import java.util.Map;

/**
 * Created by chenhao.ye on 12/03/2018.
 */
public class DemoTest {

    public static void main(String[] args) {
        StackTraceElement[] el = Thread.currentThread().getStackTrace();
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        System.out.println(map.size() + "^^^^^^^^^^^^^^");
        for (StackTraceElement[] el2 : map.values()) {
            for (int i = 0; i < el2.length - 1; i++) {
                System.out.println(el[i] + "^^^^^^^^^^^^^^^" + el[i].getMethodName());
            }
        }
        System.out.println(el.length);
        System.out.println(el[0] + "^^^^^^^^^^^^^^^" + el[0].getMethodName());
        System.out.println(el[1].getClassName() + "^^^^^^^^^^^^^^^" + el[1].getMethodName());
        System.out.println(el[2].getClassName() + "^^^^^^^^^^^^^^^" + el[2].getMethodName());
        System.out.println(el[3].getClassName() + "^^^^^^^^^^^^^^^" + el[3].getMethodName());
        MethodTest test = new MethodTest();
        test.methodTrade();
    }

}
