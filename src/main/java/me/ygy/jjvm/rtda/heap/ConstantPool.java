package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.ConstantInfo;
import me.ygy.jjvm.classfile.constant.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class ConstantPool {

    private Clazz clazz;
    private Object[] constants;

    public Object getConstant(int index) {
        if (index < 0 || index >= constants.length)
            throw new IndexOutOfBoundsException("No constants at index "+index);
        return this.constants[index];
    }

    public ConstantPool(Clazz clazz, me.ygy.jjvm.classfile.constant.ConstantPool cp) {
        int cpCount = cp.getConstantInfos().length;
        this.constants = new Object[cpCount];
        this.clazz = clazz;
        for (int i = 1; i < cp.getConstantInfos().length; i++) {
            ConstantInfo info = cp.getConstantInfo(i);
            if (info instanceof ConstantIntegerInfo) {
                this.constants[i] = ((ConstantIntegerInfo) info).getVal();
            } else if (info instanceof ConstantFloatInfo) {
                this.constants[i] = ((ConstantFloatInfo) info).getVal();
            } else if (info instanceof ConstantLongInfo) {
                this.constants[i] = ((ConstantLongInfo) info).getVal();
            } else if (info instanceof ConstantDoubleInfo) {
                this.constants[i] = ((ConstantDoubleInfo) info).getVal();
            } else if (info instanceof ConstantStringInfo) {
                this.constants[i] = info.toString();
            } else if (info instanceof ConstantUtf8Info) {
                // todo newConstantUtf8
//                this.constants.add()
            } else if (info instanceof ConstantClassInfo) {
                // newConstantClass
                this.constants[i] = new ClassRef(this, (ConstantClassInfo) info);
            } else if (info instanceof ConstantFieldrefInfo) {
                // newConstantFieldref
                this.constants[i] = new FieldRef(this, (ConstantFieldrefInfo) info);
            } else if (info instanceof ConstantMemberrefInfo) {
                // newConstantMethodref
                this.constants[i] = new MethodRef(this, (ConstantMemberrefInfo) info);
            } else if (info instanceof ConstantInterfaceMethodrefInfo) {
                // newConstantInterfaceMethodref
                this.constants[i] = new InterfaceMethodRef(this, (ConstantInterfaceMethodrefInfo) info);
            } else if (info instanceof ConstantInvokeDynamicInfo) {
                // todo newConstantInvokeDynamic
            } else if (info instanceof ConstantMethodHandleInfo) {
                // todo newConstantMethodHandle
            } else if (info instanceof ConstantMethodTypeInfo) {
                // todo newConstantMethodType
            }
        }
    }

    public Clazz getClazz() {
        return clazz;
    }

    public Object[] getConstants() {
        return constants;
    }
}
