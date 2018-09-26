package com.test.threadlocal;

/**
 * @author chenhao.ych
 * @date 2018-08-28
 */
public class ThreadLocalTest {


    public static void main(String[] args) {
//        final ThreadLocal threadLocal = new ThreadLocal();
        final ThreadLocal threadLocal = new InheritableThreadLocal();
        threadLocal.set("yechenhao");
        Thread t = new Thread(() -> {
            System.out.println(threadLocal.get());
        });

        t.start();

    }

}
