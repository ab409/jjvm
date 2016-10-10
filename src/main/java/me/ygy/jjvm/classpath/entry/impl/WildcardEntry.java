package me.ygy.jjvm.classpath.entry.impl;

import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.entry.Entry;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 11:59.
 */
public class WildcardEntry implements Entry {

    private List<Entry> entries;

    public WildcardEntry(String path) {
        String baseDir = path.substring(0, path.length() - 1);
        File file = new File(path);
        if (!file.exists() || !file.isDirectory())
            throw new IllegalArgumentException("wildcard classpath is not valid, path: "+path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) continue;
            String name = f.getName();
            if (name.endsWith(".jar") || name.endsWith(".JAR") ||
                    name.endsWith(".zip") || name.endsWith(".ZIP")) {
                entries.add(i, new ZipEntry(name));
            }
        }
    }

    public ClassData readClass(String className) throws IllegalArgumentException, IOException {
        for (Entry entry : entries) {
            ClassData classData = entry.readClass(className);
            if (classData != null)
                return classData;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.join(File.pathSeparator, this.entries.stream().map(Object::toString).collect(Collectors.toList()));
    }
}
