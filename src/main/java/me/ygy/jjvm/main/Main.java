package me.ygy.jjvm.main;

import com.google.common.base.Strings;
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

    public static void startJvm(JavaCmd cmd) throws IOException {
        System.out.println("help flag: "+cmd.isHelpFlag());
        System.out.println("version flag: "+cmd.isVersionFlag());
        System.out.println("classpath: "+cmd.getCpOption());
        System.out.println("args: "+cmd.getArgs());
        System.out.println("start main class: "+cmd.getClazz());

        Classpath classpath = Classpath.newClasspath(cmd.getxJreOption(), cmd.getCpOption());
        String className = cmd.getClazz().replaceAll("\\.", "/");
        ClassData classData = classpath.readClass(className);
        System.out.println(String.format("class data: %s\n", new String(classData.getData())));
    }

    public static void printUsage() {
        System.out.println("Usage: jjava [-options] class [args...]\n");
    }
}
