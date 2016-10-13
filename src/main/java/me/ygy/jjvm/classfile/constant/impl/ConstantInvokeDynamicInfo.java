package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:52.
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {

    private ConstantPool constantPool;
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }
}
