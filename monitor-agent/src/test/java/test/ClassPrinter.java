package test;

import jdk.internal.org.objectweb.asm.*;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;

/**
 * Created by chenhao.ye on 24/01/2018.
 */
public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(ASM4);
    }

    public ClassPrinter(int i) {
        super(i);
    }

    public ClassPrinter(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
//        System.out.println(s + " extends " + s2 + " {");
    }

    @Override
    public void visitSource(String s, String s1) {
        super.visitSource(s, s1);
    }

    @Override
    public void visitOuterClass(String s, String s1, String s2) {
        super.visitOuterClass(s, s1, s2);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
        return super.visitTypeAnnotation(i, typePath, s, b);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
    }

    @Override
    public void visitInnerClass(String s, String s1, String s2, int i) {
        super.visitInnerClass(s, s1, s2, i);
    }

    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        return super.visitField(i, s, s1, s2, o);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        return super.visitMethod(i, s, s1, s2, strings);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    public static void main(String[] args) throws Exception {
        // 字节码消费者
        ClassPrinter cp = new ClassPrinter();
        // 字节码生产者
        ClassReader cr = new ClassReader("java.lang.String");
        cr.accept(cp, 0);
    }
}
