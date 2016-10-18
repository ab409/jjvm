package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.MemberInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class ClassMember {

    private int accessFlag;
    private String name;
    private String descriptor;
    private Clazz clazz;

    public ClassMember(MemberInfo memberInfo) {
        this.accessFlag = memberInfo.getAccessFlags();
        this.name = memberInfo.name();
        this.descriptor = memberInfo.descriptor();
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
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

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
