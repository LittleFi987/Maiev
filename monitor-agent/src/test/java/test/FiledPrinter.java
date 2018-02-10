package test;

import jdk.internal.org.objectweb.asm.*;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;

/**
 * Created by chenhao.ye on 24/01/2018.
 */
public class FiledPrinter extends FieldVisitor {

    public FiledPrinter() {
        super(ASM4);
    }

    public FiledPrinter(int i) {
        super(i);
    }

    public FiledPrinter(int i, FieldVisitor fieldVisitor) {
        super(i, fieldVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        System.out.println("[visitAnnotation] 第一个参数: " + s + "第二个参数: " + b);
        return super.visitAnnotation(s, b);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String s, boolean b) {
        System.out.println("[visitTypeAnnotation] 第一个参数: " + i + "第二个参数: " + typePath.toString() + "第三个参数: " + b);
        return super.visitTypeAnnotation(i, typePath, s, b);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        System.out.println("[visitAttribute]" + attribute.toString());
        super.visitAttribute(attribute);
    }

    @Override
    public void visitEnd() {
        System.out.println("end!");
        super.visitEnd();
    }


    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("test.Agent");
        FiledPrinter filedPrinter = new FiledPrinter();
//        classReader.accept(filedPrinter, 0);
    }
}
