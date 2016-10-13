package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:51.
 */
public class ConstantMethodTypeInfo implements ConstantInfo {

    private int descriptorIndex;

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }
}
