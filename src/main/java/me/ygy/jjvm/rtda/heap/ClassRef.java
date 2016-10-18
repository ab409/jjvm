package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.constant.impl.ConstantClassInfo;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class ClassRef extends SymRef {

    public ClassRef(ConstantPool constantPool, ConstantClassInfo info) {
        this.setConstantPool(constantPool);
        this.setClassName(info.name());
    }
}
