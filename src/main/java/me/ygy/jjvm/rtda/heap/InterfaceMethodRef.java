package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantInterfaceMethodrefInfo;

import java.io.IOException;

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

    public Method resolvedInterfaceMethod() throws IOException {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolveInterfaceMethodRef() throws IOException {
        Clazz from = this.getConstantPool().getClazz();
        Clazz to = this.resolvedClass();
        if (!to.isInterface()) {
            throw new IncompatibleClassChangeError("methodref should  be interface method ref");
        }
        Method method = Method.lookupInterfaceMethod(to, this.getName(), this.getDescriptor());
        if (method == null) {
            throw new NoSuchMethodError("can not find a method "+this.getName()+" in interface "+to.getName());
        }
        if (!method.isAccessibleTo(from)) {
            throw new IllegalAccessError("can not access method "+this.getName()+"from "+from.getName()+" to "+to.getName());
        }
        this.method = method;
    }

}

