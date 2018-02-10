package classloader;

/**
 * Created by chenhao.ye on 16/11/2017.
 */
public class Example {

    public static void main(String[] args) {
        /**
         *
         * 类的加载顺序  Boostrap ClassLoader  ->  Extention ClassLoader %JRE_HOME%\lib\ext  ->  AppClassLoader
         *
         */
        String property = System.getProperty("sun.boot.class.path");
        String[] split = property.split(":");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }


}
