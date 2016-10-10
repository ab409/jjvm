package me.ygy.jjvm.classpath.impl;

import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.Entry;

import java.io.*;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipFile;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 12:00.
 */
public class ZipEntry implements Entry {

    private static int BUFFER_SIZE = 1024;

    private String absPath;

    public ZipEntry(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("zip entry is invalid, path: "+path);
        }
        this.absPath = path;
    }

    public ClassData readClass(String className) throws IllegalArgumentException, IOException {
        ZipFile zipFile = new ZipFile(absPath);
        Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            java.util.zip.ZipEntry zipEntry = entries.nextElement();
            if (zipEntry.isDirectory()) continue;
            if (zipEntry.getName().equals(className)) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                byte[] bytes = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(bytes, 0, BUFFER_SIZE)) > 0) {
                    out.write(bytes, 0, len);
                }
                return new ClassData(out.toByteArray(), this);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.absPath;
    }
}
