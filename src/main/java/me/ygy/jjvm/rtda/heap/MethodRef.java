package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantMemberrefInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class MethodRef extends MemberRef {

    private Method method;

    public MethodRef(ConstantPool constantPool, ConstantMemberrefInfo refInfo) {
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
