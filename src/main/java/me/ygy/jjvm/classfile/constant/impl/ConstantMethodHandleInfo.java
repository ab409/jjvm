package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:52.
 */
public class ConstantMethodHandleInfo implements ConstantInfo {

    private byte referenceKind;
    private int referenceIndex;

    public byte getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }
}
