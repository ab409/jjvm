package me.ygy.jjvm.classfile;

import me.ygy.jjvm.classfile.attribute.AttributeInfo;
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
            memberInfos.add(readMember(reader, pool));
        }
        return memberInfos;
    }

    public static MemberInfo readMember(ClassReader reader, ConstantPool pool) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.constantPool = pool;
        memberInfo.accessFlags = reader.readUint16();
        memberInfo.nameIndex = reader.readUint16();
        memberInfo.descriptorIndex = reader.readUint16();
        memberInfo.attributes = AttributeInfo.readAttributes(reader, pool);
        return memberInfo;
    }
}
