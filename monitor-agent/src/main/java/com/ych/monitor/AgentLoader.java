package com.ych.monitor;

import javassist.*;

import java.io.IOException;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
public class AgentLoader {


    private String className;

    private ClassLoader classLoader;

    private CtClass ctClass;

    public static final String AGENT_NAME = "$agent";


    public AgentLoader(String className, ClassLoader classLoader, CtClass ctClass) {
        this.className = className;
        this.classLoader = classLoader;
        this.ctClass = ctClass;
    }


    public void updateMethod(CtMethod method, MthodSrcBuild build) throws CannotCompileException {
        CtMethod ctMethod = method;
        String methodName = method.getName();
        CtMethod agentMethod = CtNewMethod.copy(ctMethod, methodName + AGENT_NAME, ctClass, null);
        ctClass.addMethod(agentMethod);
        method.setBody(build.buildSrc(method));
    }


    public byte[] toBytecode() throws IOException, CannotCompileException {
        return ctClass.toBytecode();
    }


    public static class MthodSrcBuild {
        public String beginSrc;

        public String endSrc;

        public String errorSrc;

        public String getBeginSrc() {
            return beginSrc;
        }

        public void setBeginSrc(String beginSrc) {
            this.beginSrc = beginSrc;
        }

        public String getEndSrc() {
            return endSrc;
        }

        public void setEndSrc(String endSrc) {
            this.endSrc = endSrc;
        }

        public String getErrorSrc() {
            return errorSrc;
        }

        public void setErrorSrc(String errorSrc) {
            this.errorSrc = errorSrc;
        }

        public String buildSrc(CtMethod method) {
            try {
                String template = method.getReturnType().getName().equals("void") ? voidSource : source;
                String bSrc = beginSrc == null ? "" : beginSrc;
                String eSrc = errorSrc == null ? "" : errorSrc;
                String enSrc = endSrc == null ? "" : endSrc;
                String src = String.format(template, bSrc, method.getName(), eSrc, enSrc);
                return src;
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }



        private final static String source = "{\n"
                + "%s"
                + "         Object result = null;\n"
                + "        try {\n"
                + "          result = ($w)%s$agent($$);\n"
                + "         } catch (Throwable e) {\n"
                + "%s"
                + "        throw e;\n"
                + "        }finally {\n"
                + "%s"
                + "        }\n"
                + "      return ($r)result;\n"
                + "}\n";


        private final static String voidSource = "{\n"
                + "%s"
                + "        try {\n"
                + "          %s$agent($$);\n"
                + "         } catch (Throwable e) {\n"
                + "%s"
                + "        throw e;\n"
                + "        }finally {\n"
                + "%s"
                + "        }\n"
                + "}\n";
    }


}
