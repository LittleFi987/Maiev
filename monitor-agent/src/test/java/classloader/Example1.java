package classloader;

/**
 * Created by chenhao.ye on 16/11/2017.
 */
public class Example1 {

    public static void main(String[] args) {
        ClassLoader classLoader = Example.class.getClassLoader();
        System.out.println("ClassLoader is :" + classLoader.toString());
        System.out.println("ClassLoader parent is :" + classLoader.getParent().toString());
        System.out.println("ClassLoader grand parent is :" + classLoader.getParent().getParent().toString());
    }

}
