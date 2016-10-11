package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;
import me.ygy.jjvm.classfile.constant.NameAndType;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:44.
 */
public class ConstantMemberrefInfo implements ConstantInfo {

    private ConstantPool pool;
    private short classIndex;
    private short nameAndTypeIndex;

    public ConstantMemberrefInfo(ConstantPool pool) {
        this.pool = pool;
    }

    public short getClassIndex() {
        return classIndex;
    }

    public short getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    public String className() {
        return this.pool.getClassName(this.classIndex);
    }

    public NameAndType nameAndDescriptor() {
        return this.pool.getNameAndType(this.nameAndTypeIndex);
    }
}
