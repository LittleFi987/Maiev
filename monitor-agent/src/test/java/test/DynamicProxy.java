package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by chenhao.ye on 16/11/2017.
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Date begin = new Date();

        Object invoke = method.invoke(target, args);

        Date end = new Date();

        long l = begin.getTime() - end.getTime();
        System.out.println("use time: " + l);
        return invoke;
    }
}
