package me.ygy.jjvm.main;

import me.ygy.jjvm.classfile.ClassFile;
import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.Classpath;
import me.ygy.jjvm.cmd.JavaCmd;

import java.io.IOException;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/9/30 18:39.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        JavaCmd cmd;
        try {
            cmd = JavaCmd.newCmd(args);
        } catch (Exception e) {
            printUsage();
            return;
        }
        if (cmd.isHelpFlag()) {
            printUsage();
        } else if (cmd.isVersionFlag()) {
            System.out.println("version 1");
        } else {
            startJvm(cmd);
        }
    }

    private static void soutLine() {
        System.out.println("================================================================================================");
    }

    public static void startJvm(JavaCmd cmd) throws IOException {
        soutLine();
        System.out.println("help flag: "+cmd.isHelpFlag());
        System.out.println("version flag: "+cmd.isVersionFlag());
        System.out.println("jre: "+cmd.getxJreOption());
        System.out.println("classpath: "+cmd.getCpOption());
        System.out.println("args: "+cmd.getArgs());
        System.out.println("start main class: "+cmd.getClazz());

        soutLine();
        Classpath classpath = Classpath.newClasspath(cmd.getxJreOption(), cmd.getCpOption());
        String className = cmd.getClazz().replaceAll("\\.", "/");
//        ClassData classData = classpath.readClass(className);
//        System.out.println(String.format("class data: %s\n", new String(classData.getData())));

        soutLine();
        ClassFile classFile = loadClass(className, classpath);
        printClassInfo(classFile);
    }

    private static ClassFile loadClass(String className, Classpath classpath) throws IOException {
        ClassData classData = classpath.readClass(className);
        ClassFile classFile = new ClassFile(classData.getData());
        return classFile;
    }

    private static void printClassInfo(ClassFile classFile) {
        System.out.println(String.format("version: %d.%d", classFile.getMajorVersion(), classFile.getMinorVersion()));
        System.out.println(String.format("constants count: %d", classFile.getConstantPool().getConstantInfos().length));
        System.out.println(String.format("access flags: 0x%X", classFile.getAccessFlags()));
        System.out.println(String.format("this class: %s", classFile.className()));
        System.out.println(String.format("super class: %s", classFile.superClassName()));
        System.out.println(String.format("interfaces: ", String.join(" ", classFile.interfaceNames())));
        System.out.println(String.format("fields count: %d", classFile.getFields().size()));
        classFile.getFields().forEach(field-> System.out.println(" "+field.name()));
        System.out.println(String.format("methods count: %d", classFile.getMethods().size()));
        classFile.getMethods().forEach(method-> System.out.println(" "+method.name()));
    }

    public static void printUsage() {
        System.out.println("Usage: jjava [-options] class [args...]\n");
    }
}
