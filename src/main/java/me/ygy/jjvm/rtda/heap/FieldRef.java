package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantFieldrefInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class FieldRef extends MemberRef {

    private Field field;

    public FieldRef(ConstantPool constantPool, ConstantFieldrefInfo refInfo) {
        this.setConstantPool(constantPool);
        this.copyMemberRefInfo(refInfo);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
