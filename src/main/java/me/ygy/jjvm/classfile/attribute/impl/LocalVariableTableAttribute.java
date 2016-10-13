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
public class LocalVariableTableAttribute implements AttributeInfo {

    public static class LocalVariableTableEntry {
        int startPc;
        int length;
        int nameIndex;
        int descriptorIndex;
        int index;
    }

    private List<LocalVariableTableEntry> localVariableTable;

    @Override
    public void readInfo(ClassReader reader) {
        int localVariableTableLength = reader.readUint16();
        this.localVariableTable = new ArrayList<>(localVariableTableLength);
        for (int i = 0; i < localVariableTableLength; i++) {
            LocalVariableTableEntry entry = new LocalVariableTableEntry();
            entry.startPc = reader.readUint16();
            entry.length = reader.readUint16();
            entry.nameIndex = reader.readUint16();
            entry.descriptorIndex = reader.readUint16();
            entry.index = reader.readUint16();
            this.localVariableTable.add(entry);
        }
    }
}
