package me.ygy.jjvm.classfile.constant;

import me.ygy.jjvm.classfile.ClassReader;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 12:16.
 */
public class ConstantPool {

    private ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader reader) {
        int cpCount = reader.readUint16();
        this.constantInfos = new ConstantInfo[cpCount];
        for (int i = 1; i < cpCount; i++) {

        }
    }

    public ConstantInfo getConstantInfo(short index) {

    }

    public String getClassName(short index) {

    }

    public String getUtf8(short index) {

    }

    public NameAndType getNameAndType(short nameAndTypeIndex) {

    }
}
