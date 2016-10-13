package me.ygy.jjvm.classfile.attribute.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.AttributeInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 17:14.
 */
public class CodeAttribute implements AttributeInfo {

    private class ExceptionTableEntry {
        int startPc;
        int endPc;
        int handlerPc;
        int catchType;

        ExceptionTableEntry(int startPc, int endPc, int handlerPc, int catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
        }
    }

    private ConstantPool pool;
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private List<ExceptionTableEntry> exceptionTable;
    private List<AttributeInfo> attributes;

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        int codeLength = reader.readUint32();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptiontable(reader);
        this.attributes = AttributeInfo.readAttributes(reader, this.pool);
    }

    private List<ExceptionTableEntry> readExceptiontable(ClassReader reader) {
        int exceptionTableLength = reader.readUint16();
        ArrayList<ExceptionTableEntry> exceptions = new ArrayList<>(exceptionTableLength);
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptions.add(new ExceptionTableEntry(reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16()));
        }
        return exceptions;
    }
}
