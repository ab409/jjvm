package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:39.
 */
public class ConstantNameAndTypeInfo implements ConstantInfo {

    private int nameIndex;
    private int descriptorIndex;

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }
}
