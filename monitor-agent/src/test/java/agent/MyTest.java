package agent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Created by chenhao.ye on 17/11/2017.
 */
public class MyTest {


    // -javaagent:/Users/chenhaoye/IdeaProjects/monitor/monitor-agent/target/monitor-agent-1.0.jar

    public void sayHello() {
        try {
            Thread.sleep(2000);
            System.out.println("hello world!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sayHello2(String hello) {
        try {
            Thread.sleep(1000);
            System.out.println(hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Class<? extends MyTest> sayHello = new ByteBuddy().subclass(MyTest.class)
                .method(ElementMatchers.named("sayHello"))
                .intercept(MethodDelegation.to(MyServiceIntereptor.class))
                .make()
                .load(MyTest.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        MyTest test = (MyTest) sayHello.newInstance();
        test.sayHello();


    }


}
