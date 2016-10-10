package me.ygy.main;

import me.ygy.cmd.JavaCmd;
import org.apache.commons.cli.ParseException;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/9/30 18:39.
 */
public class Main {

    public static void main(String[] args) {
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

    public static void startJvm(JavaCmd cmd) {
        System.out.println("help flag: "+cmd.isHelpFlag());
        System.out.println("version flag: "+cmd.isVersionFlag());
        System.out.println("classpath: "+cmd.getCpOption());
        System.out.println("args: "+cmd.getArgs());
        System.out.println("start main class: "+cmd.getClazz());
    }

    public static void printUsage() {
        System.out.println("Usage: jjava [-options] class [args...]\n");
    }
}
