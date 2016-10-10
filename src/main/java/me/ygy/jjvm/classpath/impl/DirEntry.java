package me.ygy.jjvm.classpath.impl;

import com.google.common.base.Strings;
import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.Entry;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 12:00.
 */
public class DirEntry implements Entry {

    private String absDir;

    public String getAbsDir() {
        return absDir;
    }

    public void setAbsDir(String absDir) {
        this.absDir = absDir;
    }

    public DirEntry(String path) {
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("dir classpath is invalid, path="+path);
        }
        this.absDir = dir.getAbsolutePath();
    }

    public ClassData readClass(String className) throws IllegalArgumentException, IOException {
        File classFile = new File(this.absDir, className);
        FileInputStream fileInputStream = new FileInputStream(classFile);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
        while (channel.read(buffer) > 0){}
        return new ClassData(buffer.array(), this);
    }

    @Override
    public String toString() {
        return this.absDir;
    }
}
