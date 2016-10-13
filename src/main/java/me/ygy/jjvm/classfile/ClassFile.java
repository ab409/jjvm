package me.ygy.jjvm.classfile;

import me.ygy.jjvm.classfile.attribute.AttributeInfo;
import me.ygy.jjvm.classfile.constant.ConstantPool;

import java.util.List;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 11:48.
 */
public class ClassFile {

    private int magic;
    private int minorVersion;
    private int majorVersion;
    //constant pool
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int[] interfaces;
    //memberInfo attibuteInfo
    private List<MemberInfo> fields;
    private List<MemberInfo> methods;
    private List<AttributeInfo> attributes;

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public List<MemberInfo> getFields() {
        return fields;
    }

    public List<MemberInfo> getMethods() {
        return methods;
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public int getMagic() {
        return magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        this.read(reader);
    }

    public void read(ClassReader reader) {
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        //constant pool
        this.constantPool = new ConstantPool(this, reader);

        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        //fields, methods, attributes
        this.fields = MemberInfo.readMembers(reader, this.constantPool);
        this.methods = MemberInfo.readMembers(reader, this.constantPool);
        this.attributes = AttributeInfo.readAttributes(reader, this.constantPool);
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
        return this.constantPool.getClassName(this.getThisClass());
    }

    public String superClassName() {
        if (this.getSuperClass() > 0)
            return this.constantPool.getClassName(this.superClass);
        return "";
    }

    public String[] interfaceNames() {
        String[] names = new String[this.interfaces.length];
        for (int i = 0; i <this.interfaces.length; i++) {
            int index = this.interfaces[i];
            names[i] = this.constantPool.getClassName(index);
        }
        return names;
    }
}
