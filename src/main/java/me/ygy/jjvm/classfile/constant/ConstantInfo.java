package me.ygy.jjvm.classfile.constant;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.impl.*;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 12:16.
 */
public interface ConstantInfo {

    byte CONSTANT_Class = 7;
    byte CONSTANT_Fieldref = 9;
    byte CONSTANT_Methodref = 10;
    byte CONSTANT_InterfaceMethodRef = 11;
    byte CONSTANT_String = 8;
    byte CONSTANT_Integer = 3;
    byte CONSTANT_Float = 4;
    byte CONSTANT_Long = 5;
    byte CONSTANT_Double = 6;
    byte CONSTANT_NameAndType = 12;
    byte CONSTANT_Utf8 = 1;
    byte CONSTANT_MethodHandle = 15;
    byte CONSTANT_MethodType = 16;
    byte CONSTANT_InvokeDynamic = 18;


    void readInfo(ClassReader reader);

    static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool pool) {
        byte tag = reader.readUint8();
        ConstantInfo info = newConstantInfo(tag, pool);
        info.readInfo(reader);
        return info;
    }

    static ConstantInfo newConstantInfo(byte tag, ConstantPool pool) {
        switch (tag) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_String:
                return new ConstantStringInfo(pool);
            case CONSTANT_Class:
                return new ConstantClassInfo(pool);
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo(pool);
            case CONSTANT_Methodref:
                return new ConstantMemberrefInfo(pool);
            case CONSTANT_InterfaceMethodRef:
                return new ConstantInterfaceMethodrefInfo(pool);
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new ClassFormatError("constant pool tag");
        }
    }
}
