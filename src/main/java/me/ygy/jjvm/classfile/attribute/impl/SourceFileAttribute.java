package me.ygy.jjvm.classfile.attribute.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.AttributeInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 17:17.
 */
public class SourceFileAttribute implements AttributeInfo {

    private ConstantPool pool;
    private short sourceFileIndex;

    public SourceFileAttribute(ConstantPool pool) {
        this.pool = pool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    public String fileName() {
        return this.pool.getUtf8(this.sourceFileIndex);
    }
}
