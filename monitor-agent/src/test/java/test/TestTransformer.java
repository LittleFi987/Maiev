package test;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by chenhao.ye on 14/11/2017.
 */
public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//先判断下现在加载的class的包路径是不是需要监控的类，通过instrumentation进来的class路径用‘/’分割
        if(className.startsWith("agent")){
            //将‘/’替换为‘.’m比如monitor/agent/Mytest替换为monitor.agent.Mytest
            className = className.replace("/",".");
            CtClass ctclass = null;
            try {
                //用于取得字节码类，必须在当前的classpath中，使用全称 ,这部分是关于javassist的知识
                ctclass = ClassPool.getDefault().get(className);
                //循环一下，看看哪些方法需要加时间监测
                return ctclass.toBytecode();
            } catch (IOException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            } catch (CannotCompileException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NotFoundException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
