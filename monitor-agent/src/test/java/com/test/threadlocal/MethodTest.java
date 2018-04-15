package com.test.threadlocal;

/**
 * @Author yechenhao
 * @Email yechenhao@terminus.io
 * @Date 14/04/2018 9:30 PM
 */
public class MethodTest {
   public void methodTrade() {
       StackTraceElement[] el = Thread.currentThread().getStackTrace();
       for (StackTraceElement e : el) {
           System.out.println("11111" + e.getFileName() + "==" + e.getClassName() + "==" + e.getMethodName() + "==" + e.getLineNumber());
       }
   }
}
