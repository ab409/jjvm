package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.ClassFile;

import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class Clazz {
    private int accesssFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    //todo constantpool field method
//    private ConstantPool constantPool;

    private ClassLoader classLoader;

    private Clazz superClass;
    private List<Clazz> interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    //todo slots
//    private LocalVars.Slot


    public Clazz(ClassFile classFile) {
        this.accesssFlags = classFile.getAccessFlags();
        this.name = classFile.className();
        this.superClassName = classFile.superClassName();
        this.interfaceNames = classFile.interfaceNames();
        //todo constantpool fields methods

    }

    public void setInterfaces(List<Clazz> interfaces) {
        this.interfaces = interfaces;
    }

    public void setSuperClass(Clazz superClass) {
        this.superClass = superClass;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public int getAccesssFlags() {
        return accesssFlags;
    }

    public String getName() {
        return name;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public Clazz getSuperClass() {
        return superClass;
    }

    public List<Clazz> getInterfaces() {
        return interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public boolean isPublic() {
        return (this.accesssFlags & AccessFlag.ACC_PUBLIC) != 0;
    }

    public boolean isFinal() {
        return (this.accesssFlags & AccessFlag.ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (this.accesssFlags & AccessFlag.ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (this.accesssFlags & AccessFlag.ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (this.accesssFlags & AccessFlag.ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (this.accesssFlags & AccessFlag.ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (this.accesssFlags & AccessFlag.ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (this.accesssFlags & AccessFlag.ENUM) != 0;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    @Override
    public String toString() {
        return "{Class name: "+this.name+"}";
    }
}
