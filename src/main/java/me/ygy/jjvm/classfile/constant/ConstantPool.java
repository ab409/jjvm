package me.ygy.jjvm.classfile.constant;

import me.ygy.jjvm.classfile.ClassFile;
import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.impl.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 12:16.
 */
public class ConstantPool {

    private ConstantInfo[] constantInfos;

    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public ConstantPool(ClassFile classFile, ClassReader reader) {
        int cpCount = reader.readUint16();
        this.constantInfos = new ConstantInfo[cpCount];
        for (int i = 1; i < cpCount; i++) {
            this.constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);
            if (this.constantInfos[i] instanceof ConstantDoubleInfo || this.constantInfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }
    }

    public ConstantInfo getConstantInfo(int index) {
        if (index >= constantInfos.length)
            throw new IndexOutOfBoundsException("invalid constant pool index:"+index);
        ConstantInfo constantInfo = constantInfos[index];
        if (null == constantInfo) {
            throw new IndexOutOfBoundsException("invalid constant pool index: "+index);
        }
        return constantInfo;
    }

    public String getClassName(int index) {
        ConstantClassInfo info = (ConstantClassInfo) this.getConstantInfo(index);
        return this.getUtf8(info.getNameIndex());
    }

    public String getUtf8(int index) {
        ConstantInfo temp = this.getConstantInfo(index);
        ConstantUtf8Info info;
        if (temp instanceof ConstantUtf8Info) {
            info = (ConstantUtf8Info) this.getConstantInfo(index);
        } else {
            throw new IllegalArgumentException("class file is not valid, utf8 constant is invalid");
        }
        return info.getStr();
    }

    public NameAndType getNameAndType(int nameAndTypeIndex) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) this.getConstantInfo(nameAndTypeIndex);
        String name = this.getUtf8(info.getNameIndex());
        String type = this.getUtf8(info.getDescriptorIndex());
        return new NameAndType(name, type);
    }
}
