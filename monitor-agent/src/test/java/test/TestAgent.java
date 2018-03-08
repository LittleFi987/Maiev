package test;

/**
 * Created by chenhao.ye on 14/11/2017.
 */
public class TestAgent {

    public static void main(String[] args) throws Exception{
//        while(true) {
//            Thread.sleep(1000);
            TestAgent testAgent = new TestAgent();
            testAgent.test();
//            Thread.sleep(3000);
            Demo demo = new Demo();
//        }
    }


    public void test() {
        System.out.println("I am TestAgent");
    }

}
