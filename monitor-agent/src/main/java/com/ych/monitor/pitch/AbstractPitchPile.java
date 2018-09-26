package com.ych.monitor.pitch;

import com.ych.monitor.interceptor.MethodsAroundInterceptor;
import javassist.CtMethod;
import javassist.Modifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenhao.ych
 * @date 2018-09-03
 */
public abstract class AbstractPitchPile implements PitchPile {

    private MethodsAroundInterceptor interceptor;

    private String methodName;

    private String returnTypeName;

    public AbstractPitchPile(MethodsAroundInterceptor interceptor, String methodName, String returnTypeName) {
        this.interceptor = interceptor;
        this.methodName = methodName;
        this.returnTypeName = returnTypeName;
    }

    protected static final String AGENT_NAME = "$agent";

    private final static String SOURCE = "{\n"
            + "%s"
            + "         Object result = null;\n"
            + "        try {\n"
            + "          result = ($w)%s" + AGENT_NAME + "($$);\n"
            + "         } catch (Throwable e) {\n"
            + "%s"
            + "        throw e;\n"
            + "        }finally {\n"
            + "%s"
            + "        }\n"
            + "      return ($r)result;\n"
            + "}\n";


    private final static String VOID_SOURCE = "{\n"
            + "%s"
            + "        try {\n"
            + "          %s " + AGENT_NAME + "($$);\n"
            + "         } catch (Throwable e) {\n"
            + "%s"
            + "        throw e;\n"
            + "        }finally {\n"
            + "%s"
            + "        }\n"
            + "}\n";


    public String buildSrc() {
        String template = returnTypeName.equals("void") ? VOID_SOURCE : SOURCE;
        return String.format(template, interceptor.beforeMethod(),
                methodName, interceptor.handleMethodException(),
                interceptor.afterMethod());
    }

    protected Boolean verifyMethod(CtMethod ctMethod) {
        // 屏蔽非公共方法
        if (!Modifier.isPublic(ctMethod.getModifiers())) {
            return false;
        }
        // 屏蔽静态方法
        if (Modifier.isNative(ctMethod.getModifiers())) {
            return false;
        }
        // 屏蔽本地方法
        if (Modifier.isStatic(ctMethod.getModifiers())) {
            return false;
        }
        return true;
    }

    protected static String getAnnotationValue(String key, String annotationDesc) {
        String regex = String.format("value=\\{\".*\"\\}");
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(annotationDesc);
        if (matcher.find()) {
            return matcher.group().substring(key.length() + 3, matcher.group().length() - 2);
        }
        return null;
    }




}
