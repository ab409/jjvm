package me.ygy.jjvm.classpath.entry.impl;

import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.entry.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 11:58.
 */
public class CompositeEntry implements Entry {

    private List<Entry> entries;

    public CompositeEntry(String pathList) {
        String[] pathArray = pathList.split(File.pathSeparator);
        this.entries = new ArrayList<>(pathArray.length);
        for (int i = 0; i < pathArray.length; i++) {
            String path = pathArray[i];
            Entry entry = Entry.newEntry(path);
            this.entries.add(i, entry);
        }
    }

    public ClassData readClass(String className) throws IllegalArgumentException, IOException {
        for (Entry entry : this.entries) {
            ClassData classData = entry.readClass(className);
            if (classData != null) {
                return classData;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.join(File.pathSeparator, this.entries.stream().map(Object::toString).collect(Collectors.toList()));
    }
}
