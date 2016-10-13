package me.ygy.jjvm.classfile.attribute.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.AttributeInfo;

import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 17:16.
 */
public class ExceptionsAttribute implements AttributeInfo {

    private int[] exceptionIndexTable;

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }
}
