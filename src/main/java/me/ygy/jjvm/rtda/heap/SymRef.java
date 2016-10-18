package me.ygy.jjvm.rtda.heap;

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
}
