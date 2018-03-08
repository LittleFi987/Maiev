import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenhao.ye on 17/11/2017.
 */
public class MyTest1 {


    private static final Logger logger = LoggerFactory.getLogger(MyTest1.class);

    public static void main(String[] args) {
        logger.info("i am log ->>>>>>");
        sayHello();
        sayHello2("hello world222222222");
    }
    public static void sayHello() {
        try {
            Thread.sleep(2000);
            System.out.println("hello world!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sayHello2(String hello) {
        try {
            Thread.sleep(1000);
            System.out.println(hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
