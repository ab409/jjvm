package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantMemberrefInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class MemberRef extends SymRef {

    private String name;
    private String descriptor;

    public void copyMemberRefInfo(ConstantMemberrefInfo refInfo) {
        this.setClassName(refInfo.className());
        this.setName(refInfo.nameAndDescriptor().getName());
        this.setDescriptor(refInfo.nameAndDescriptor().getType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
