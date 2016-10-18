package me.ygy.jjvm.rtda.heap;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class SymRef {

    private ConstantPool constantPool;
    private String className;
    private Clazz clazz;

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Clazz resolvedClass() throws IOException {
        if (this.clazz == null) {
            this.resolvedClassRef();
        }
        return this.clazz;
    }

    public void resolvedClassRef() throws IOException {
        Clazz other = this.constantPool.getClazz();
        Clazz c = other.getClassLoader().loadClass(this.className);
        if (!c.isAccessibleTo(other)) {
            throw new IllegalAccessError(String.format("class %s is not accessible to %s", this.className, other.getName()));
        }
        this.clazz = c;
    }
}
