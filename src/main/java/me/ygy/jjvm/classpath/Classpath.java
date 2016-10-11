package me.ygy.jjvm.classpath;

import com.google.common.base.Strings;
import me.ygy.jjvm.classpath.entry.Entry;
import me.ygy.jjvm.classpath.entry.impl.WildcardEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 17:55.
 */
public class Classpath {

    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;

    public Entry getBootClasspath() {
        return bootClasspath;
    }

    public void setBootClasspath(Entry bootClasspath) {
        this.bootClasspath = bootClasspath;
    }

    public Entry getExtClasspath() {
        return extClasspath;
    }

    public void setExtClasspath(Entry extClasspath) {
        this.extClasspath = extClasspath;
    }

    public Entry getUserClasspath() {
        return userClasspath;
    }

    public void setUserClasspath(Entry userClasspath) {
        this.userClasspath = userClasspath;
    }

    public static Classpath newClasspath(String jreOption, String cpOption) {
        Classpath classpath = new Classpath();
        classpath.parseBootAndExtClasspath(jreOption);
        classpath.parseUserClasspath(cpOption);
        return classpath;
    }

    public ClassData readClass(String className) throws IOException {
        className += ".class";
        ClassData classData = this.bootClasspath.readClass(className);
        if (classData != null)
            return classData;
        classData = this.extClasspath.readClass(className);
        if (classData != null)
            return classData;
        return this.userClasspath.readClass(className);
    }

    private void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        String jreLibPath = Paths.get(jreDir, "lib").toString() + "\\*";
        this.bootClasspath = new WildcardEntry(jreLibPath);
        String jreExtPath = Paths.get(jreDir, "lib", "ext").toString() + "\\*";
        this.extClasspath = new WildcardEntry(jreExtPath);
    }

    private String getJreDir(String jreOption) {
        if (!Strings.isNullOrEmpty(jreOption)) {
            return jreOption;
        }
        File currentJre = new File("./jre");
        if (currentJre.exists()) {
            return "./jre";
        }
        String jh = System.getenv("JAVA_HOME");
        if (!Strings.isNullOrEmpty(jh)) {
            return Paths.get(jh, "jre").toString();
        }
        throw new IllegalArgumentException("can not find jre folder");
    }

    private void parseUserClasspath(String cpOption) {
        if (Strings.isNullOrEmpty(cpOption))
            cpOption = ".";
        this.userClasspath = Entry.newEntry(cpOption);
    }
}
