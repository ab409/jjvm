package me.ygy.jjvm.classfile;

import me.ygy.jjvm.classfile.constant.ConstantPool;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 11:48.
 */
public class ClassFile {

    private int magic;
    private short minorVersion;
    private short majorVersion;
    //constant pool
    private ConstantPool constantPool;
    private short accessFlags;
    private short thisClass;
    private short superClass;
    private short[] interfaces;
    //todo memberInfo attibuteInfo


    public int getMagic() {
        return magic;
    }

    public short getMinorVersion() {
        return minorVersion;
    }

    public short getMajorVersion() {
        return majorVersion;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public short getThisClass() {
        return thisClass;
    }

    public short getSuperClass() {
        return superClass;
    }

    public short[] getInterfaces() {
        return interfaces;
    }

    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        this.read(reader);
    }

    public void read(ClassReader reader) {
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        //todo constant pool

        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        //todo fields, methods, attributes

    }

    public void readAndCheckMagic(ClassReader reader) {
        int magic = reader.readUint32();
        if ((magic ^ 0xCAFEBABE) != 0) {
            throw new IllegalArgumentException("class file is not java class file, magic num is not CAFEBABE");
        }
        this.magic = 0xCAFEBABE;
    }

    public void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();

        switch (this.majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion == 0) {
                    return;
                }
        }
        throw new IllegalArgumentException("class file version is not supported by this jjvm");
    }

    public String className() {

    }

    public String superClassName() {

    }

    public String[] interfaceNames() {

    }
}
