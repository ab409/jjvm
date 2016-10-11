package me.ygy.jjvm.classfile.attribute;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.impl.*;
import me.ygy.jjvm.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:57.
 */
public interface AttributeInfo {

    void readInfo(ClassReader reader);

    static List<AttributeInfo> readAttributes(ClassReader reader, ConstantPool pool) {
        short attrCount = reader.readUint16();
        List<AttributeInfo> attributeInfos = new ArrayList<>(attrCount);
        for(int i = 0; i < attrCount; i++) {
            attributeInfos.add(readAttribute(reader, pool));
        }
        return attributeInfos;
    }

    static AttributeInfo readAttribute(ClassReader reader, ConstantPool pool) {
        short attrNameIndex = reader.readUint16();
        String attrName = pool.getUtf8(attrNameIndex);
        int attrLen = reader.readUint32();
        AttributeInfo attributeInfo = newAttributeInfo(attrName, attrLen, pool);
        attributeInfo.readInfo(reader);
        return attributeInfo;
    }

    /**
     * there are 23 attributes.here we implement 8 of them
     * @param attrName
     * @param attrLen
     * @param pool
     * @return
     */
    static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool pool) {
        switch (attrName) {
            case "Code":
                return new CodeAttribute();
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "Exceptions":
                return new ExceptionsAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "SourceFile":
                return new SourceFileAttribute(pool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparseedAttribute();
        }
    }
}
