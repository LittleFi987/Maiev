package com.test;

import javassist.*;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class JavaSssistWord {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool pool = new ClassPool();
        // 插入类路径  通过类路径去搜索我们要的类
        pool.insertClassPath(new LoaderClassPath(IHello.class.getClassLoader()));

        // 构建一个新的CtClass对象
        CtClass ctClass = pool.makeClass("com.test.Hello1");

        // 实现一个接口
        ctClass.addInterface(pool.get(IHello.class.getName()));
        CtClass returnType = pool.get(void.class.getName());

        String mname = "sayHello";
        CtClass[] parameters = new CtClass[]{
                pool.get(String.class.getName())
        };

        CtMethod method = new CtMethod(returnType, mname, parameters, ctClass);
        String src = "{"
                + "System.out.println($1);"
                + "System.out.println($args);"
//                + "System.out.println($r);"
                + "System.out.println($type.toString());"
                + "System.out.println($class);"
                + "System.out.println(\"hello \" + $1);"
                + "}";
        method.setBody(src);
        ctClass.addMethod(method);
        Class aClass = ctClass.toClass();
        IHello iHello = (IHello) aClass.newInstance();
        iHello.sayHello("hello");

    }

}
