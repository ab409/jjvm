package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.MemberInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class ClassMember {

    protected int accessFlag;
    protected String name;
    protected String descriptor;
    protected Clazz clazz;

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

    public boolean isPublic() {
        return (this.accessFlag & AccessFlag.ACC_PUBLIC) != 0;
    }

    public boolean isPrivate() {
        return (this.accessFlag & AccessFlag.ACC_PRIVATE) != 0;
    }

    public boolean isProtected() {
        return (this.accessFlag & AccessFlag.ACC_PROTECTED) != 0;
    }

    public boolean isStatic() {
        return (this.accessFlag & AccessFlag.ACC_STATIC) != 0;
    }

    public boolean isFinal() {
        return (this.accessFlag & AccessFlag.ACC_FINAL) != 0;
    }

    public boolean isSynchronized() {
        return (this.accessFlag & AccessFlag.ACC_SYNCHRONIZED) != 0;
    }

    public boolean isVolatile() {
        return (this.accessFlag & AccessFlag.ACC_VOLATILE) != 0;
    }

    public boolean isBridge() {
        return (this.accessFlag & AccessFlag.ACC_BRIDGE) != 0;
    }

    public boolean isTransient() {
        return (this.accessFlag & AccessFlag.ACC_TRANSIENT) != 0;
    }

    public boolean isVarargs() {
        return (this.accessFlag & AccessFlag.ACC_VARARGS) != 0;
    }

    public boolean isNative() {
        return (this.accessFlag & AccessFlag.ACC_NATIVE) != 0;
    }

    public boolean isAbstract() {
        return (this.accessFlag & AccessFlag.ACC_ABSTRACT) != 0;
    }

    public boolean isStrict() {
        return (this.accessFlag & AccessFlag.ACC_STRICT) != 0;
    }

    public boolean isSynthetic() {
        return (this.accessFlag & AccessFlag.ACC_SYNTHETIC) != 0;
    }

    public boolean isEnum() {
        return (this.accessFlag & AccessFlag.ENUM) != 0;
    }
}
