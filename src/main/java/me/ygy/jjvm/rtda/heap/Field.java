package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.MemberInfo;
import me.ygy.jjvm.classfile.attribute.impl.ConstantValueAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Field extends ClassMember {

    private int slotId;
    private int constValueIndex;

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Field(MemberInfo memberInfo) {
        super(memberInfo);
    }

    public static List<Field> newFields(Clazz clazz, List<MemberInfo> memberInfos) {
        List<Field> list = new ArrayList<>(memberInfos.size());
        for (MemberInfo info : memberInfos) {
            Field field = new Field(info);
            field.setClazz(clazz);

            list.add(field);
        }
        return list;
    }

    public boolean isLongOrDouble() {
        return "J".equals(this.descriptor) || "D".equals(this.descriptor);
    }

    private void copyAttributes(MemberInfo info) {
        ConstantValueAttribute constantValueAttribute = info.getConstantValueAttribute();
        if (constantValueAttribute != null) {
            this.constValueIndex = constantValueAttribute.getConstantValueIndex();
        }
    }
}
