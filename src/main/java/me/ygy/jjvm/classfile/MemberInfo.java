package me.ygy.jjvm.classfile;

import me.ygy.jjvm.classfile.attribute.AttributeInfo;
import me.ygy.jjvm.classfile.attribute.impl.CodeAttribute;
import me.ygy.jjvm.classfile.attribute.impl.ConstantValueAttribute;
import me.ygy.jjvm.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 12:10.
 */
public class MemberInfo {

    //constant pool
    private ConstantPool constantPool;
    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    //attributeInfo
    private List<AttributeInfo> attributes;

    public int getAccessFlags() {
        return accessFlags;
    }

    public String name() {
        return this.constantPool.getUtf8(this.nameIndex);
    }

    public String descriptor() {
        return this.constantPool.getUtf8(this.descriptorIndex);
    }

    public static List<MemberInfo> readMembers(ClassReader reader, ConstantPool pool) {
        int memberCount = reader.readUint16();
        List<MemberInfo> memberInfos = new ArrayList<>(memberCount);
        for (int i = 0; i < memberCount; i++) {
            MemberInfo memberInfo = new MemberInfo(pool);
            memberInfo.read(reader);
            memberInfos.add(memberInfo);
        }
        return memberInfos;
    }


    public MemberInfo(ConstantPool pool) {
        this.constantPool = pool;
    }

    public void read(ClassReader reader) {
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
        this.attributes = AttributeInfo.readAttributes(reader, this.constantPool);
    }

    public CodeAttribute getCodeAttribute() {
        for (AttributeInfo info : this.attributes) {
            if (info instanceof CodeAttribute) {
                return (CodeAttribute) info;
            }
        }
        return null;
    }

    public ConstantValueAttribute getConstantValueAttribute() {
        for (AttributeInfo attribute : this.attributes) {
            if (attribute instanceof  ConstantValueAttribute) {
                return (ConstantValueAttribute) attribute;
            }
        }
        return null;
    }
}
