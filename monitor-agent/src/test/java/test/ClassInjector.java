package test;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

/**
 * Created by chenhao.ye on 15/11/2017.
 */
public class ClassInjector {
    private static Method DEFINE_CLASS;
    private static Method ADD_URL;

    static {
        try {
            DEFINE_CLASS = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            DEFINE_CLASS.setAccessible(true);

            ADD_URL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            ADD_URL.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 注入到非URLClassLoader的非引导类ClassLoader
     *
     * @param classLoader
     * @param className
     * @param bytes       类定义
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void defineClass(ClassLoader classLoader, String className, byte[] bytes) throws InvocationTargetException, IllegalAccessException {
        if (classLoader != null) {
            DEFINE_CLASS.invoke(classLoader, className, bytes, 0, bytes.length);
        }
    }

    /**
     * 注入到URLClassLoader类加载器
     *
     * @param classLoader
     * @param url
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void addURL(URLClassLoader classLoader, URL url) throws InvocationTargetException, IllegalAccessException {
        ADD_URL.invoke(classLoader, url);
    }

    /**
     * 注入到引导类加载器
     *
     * @param instrumentation
     * @param jarFile
     */
    public static void addURL(Instrumentation instrumentation, JarFile jarFile) {
        instrumentation.appendToBootstrapClassLoaderSearch(jarFile);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
    }

}
