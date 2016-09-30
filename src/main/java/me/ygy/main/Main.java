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
        JavaCmd cmd = null;
        try {
            cmd = JavaCmd.newCmd(args);
        } catch (ParseException e) {
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
        System.out.println(cmd.getClazz());
    }

    public static void printUsage() {
        System.out.println("Usage: jjava [-options] class [args...]\n");
    }
}
