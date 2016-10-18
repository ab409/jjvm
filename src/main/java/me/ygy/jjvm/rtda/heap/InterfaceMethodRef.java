package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantInterfaceMethodrefInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class InterfaceMethodRef extends MemberRef {

    private Method method;

    public InterfaceMethodRef(ConstantPool constantPool, ConstantInterfaceMethodrefInfo refInfo) {
        this.setConstantPool(constantPool);
        this.copyMemberRefInfo(refInfo);
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
