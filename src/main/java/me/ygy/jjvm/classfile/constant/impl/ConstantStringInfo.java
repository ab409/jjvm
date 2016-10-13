package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:33.
 */
public class ConstantStringInfo implements ConstantInfo {

    private ConstantPool pool;
    private int stringIndex;

    public int getStringIndex() {
        return stringIndex;
    }

    public ConstantStringInfo(ConstantPool pool) {
        this.pool = pool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    @Override
    public String toString() {
        return this.pool.getUtf8(this.stringIndex);
    }
}
