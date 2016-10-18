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
    private List<Object> constants;

    public Object getConstant(int index) {
        if (index < 0 || index >= constants.size())
            throw new IndexOutOfBoundsException("No constants at index "+index);
        return this.constants.get(index);
    }

    public ConstantPool(Clazz clazz, me.ygy.jjvm.classfile.constant.ConstantPool cp) {
        int cpCount = cp.getConstantInfos().length;
        this.constants = new ArrayList<>(cpCount);
        this.clazz = clazz;
        for (ConstantInfo info : cp.getConstantInfos()) {
            if (info instanceof ConstantIntegerInfo) {
                this.constants.add(((ConstantIntegerInfo) info).getVal());
            } else if (info instanceof ConstantFloatInfo) {
                this.constants.add(((ConstantFloatInfo) info).getVal());
            } else if (info instanceof ConstantLongInfo) {
                this.constants.add(((ConstantLongInfo) info).getVal());
            } else if (info instanceof ConstantDoubleInfo) {
                this.constants.add(((ConstantDoubleInfo) info).getVal());
            } else if (info instanceof ConstantStringInfo) {
                this.constants.add(info.toString());
            } else if (info instanceof ConstantUtf8Info) {
                // todo newConstantUtf8
//                this.constants.add()
            } else if (info instanceof ConstantClassInfo) {
                // todo newConstantClass
            } else if (info instanceof ConstantFieldrefInfo) {
                // todo newConstantFieldref
            } else if (info instanceof ConstantMemberrefInfo) {
                // todo newConstantMethodref
            } else if (info instanceof ConstantInterfaceMethodrefInfo) {
                // todo newConstantInterfaceMethodref
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

    public List<Object> getConstants() {
        return constants;
    }
}
