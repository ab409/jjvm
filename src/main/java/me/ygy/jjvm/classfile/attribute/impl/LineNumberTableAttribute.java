package me.ygy.jjvm.classfile.attribute.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.AttributeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 17:16.
 */
public class LineNumberTableAttribute implements AttributeInfo {

    public static class LineNumberTableEntry {
        int startPc;
        int lineNumber;
    }

    private List<LineNumberTableEntry> lineNumberTable;

    public List<LineNumberTableEntry> getLineNumberTable() {
        return lineNumberTable;
    }

    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new ArrayList<>(lineNumberTableLength);
        for (int i = 0; i < lineNumberTableLength; i++) {
            LineNumberTableEntry entry = new LineNumberTableEntry();
            entry.startPc = reader.readUint16();
            entry.lineNumber = reader.readUint16();
            this.lineNumberTable.add(entry);
        }
    }
}
