package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantMemberrefInfo;

import java.io.IOException;

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

    public Method resolvedMethod() throws IOException {
        if (this.method == null) {
            this.resolveMethodRef();
        }
        return this.method;
    }

    private void resolveMethodRef() throws IOException {
        Clazz from = this.getConstantPool().getClazz();
        Clazz to = this.resolvedClass();
        if (to.isInterface()) {
            throw new IncompatibleClassChangeError("methodref should not be interface method ref");
        }
        Method method = Method.lookupMethod(to, this.getName(), this.getDescriptor());
        if (method == null) {
            throw new NoSuchMethodError("can not find a method "+this.getName()+" in class "+to.getName());
        }
        if (!method.isAccessibleTo(from)) {
            throw new IllegalAccessError("can not access method "+this.getName()+"from "+from.getName()+" to "+to.getName());
        }
        this.method = method;
    }
}
