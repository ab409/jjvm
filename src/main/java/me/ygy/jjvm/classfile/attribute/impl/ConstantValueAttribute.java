package me.ygy.jjvm.classfile.attribute.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.AttributeInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 17:15.
 */
public class ConstantValueAttribute implements AttributeInfo {

    private short constantValueIndex;

    public short getConstantValueIndex() {
        return constantValueIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }
}
