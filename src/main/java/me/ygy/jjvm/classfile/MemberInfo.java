package me.ygy.jjvm.classfile;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 12:10.
 */
public class MemberInfo {

    //todo constant pool

    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    //todo attributeInfo

    public MemberInfo(ClassReader reader) {
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();

        //todo init constant pool & attributes

    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public String name() {

    }

    public String descriptor() {

    }
}
