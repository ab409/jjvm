package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantFieldrefInfo;

import java.io.IOException;

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

    public Field resolvedField() throws IOException {
        if (this.field == null) {
            this.resolvedFieldRef();
        }
        return this.field;
    }

    private void resolvedFieldRef() throws IOException {
        Clazz clazz = this.getConstantPool().getClazz();
        Clazz other = this.resolvedClass();
        Field field = Field.lookupField(other, this.getName(), this.getDescriptor());
        if (field == null) {
            throw new NoSuchFieldError(String.format("field %s is not found", this.getName()));
        }
        if (!field.isAccessibleTo(clazz)) {
            throw new IllegalAccessError(String.format("class %s' field %s is not accessible to %s",
                    other.getName(), this.getName(), clazz.getName()));
        }
        this.field = field;
    }


}
