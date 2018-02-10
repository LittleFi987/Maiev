package agent;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by chenhao.ye on 03/02/2018.
 */
public class MyServiceIntereptor {


    @RuntimeType
    public static Object intercept(@SuperCall Callable callable, @Origin Method method) throws Exception {
        System.out.println("intercept: 拦截了" + callable);
        callable.call();
        System.out.println(method);
        return 2;
    }

}
