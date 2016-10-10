package me.ygy.jjvm.classpath;

import com.google.common.base.Strings;
import me.ygy.jjvm.classpath.impl.CompositeEntry;
import me.ygy.jjvm.classpath.impl.DirEntry;
import me.ygy.jjvm.classpath.impl.WildcardEntry;
import me.ygy.jjvm.classpath.impl.ZipEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 10:10.
 */
public interface Entry {
    ClassData readClass(String className) throws IllegalArgumentException, IOException;

    static Entry newEntry(String path) {
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return WildcardEntry.newWildEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR")
                || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }
}
