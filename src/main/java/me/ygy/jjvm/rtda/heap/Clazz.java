package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.ClassFile;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.Thread;

import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class Clazz {
    private int accesssFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    //
    private ConstantPool constantPool;
    private List<Field> fields;
    private List<Method> methods;

    private ClassLoader classLoader;

    private Clazz superClass;
    private List<Clazz> interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    //
    private LocalVars staticVars;

    private boolean initStarted;

    public void startInit() {
        this.setInitStarted(true);
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public void setInitStarted(boolean initStarted) {
        this.initStarted = initStarted;
    }

    public void setAccesssFlags(int accesssFlags) {
        this.accesssFlags = accesssFlags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public LocalVars getStaticVars() {
        return staticVars;
    }

    public void setStaticVars(LocalVars staticVars) {
        this.staticVars = staticVars;
    }

    public Clazz(ClassFile classFile) {
        this.accesssFlags = classFile.getAccessFlags();
        this.name = classFile.className();
        this.superClassName = classFile.superClassName();
        this.interfaceNames = classFile.interfaceNames();
        //constantpool fields methods
        this.constantPool = new ConstantPool(this, classFile.getConstantPool());
        this.fields = Field.newFields(this, classFile.getFields());
        this.methods = Method.newMethods(this, classFile.getMethods());
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

    public boolean isAccessibleTo(Clazz other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf("/");
        if (i >= 0) {
            return this.name.substring(0, i);
        }
        return "";
    }

    public boolean isSubClassOf(Clazz c) {
        Clazz superClass = this.superClass;
        while (superClass != null) {
            if (superClass.equals(c)) {
                return true;
            }
            superClass = superClass.superClass;
        }
        return false;
    }

    public boolean isSuperClassOf(Clazz c) {
        return c.isSubClassOf(this);
    }

    public Objectz newObject() {
        return newObject(this);
    }

    public static Objectz newObject(Clazz clazz) {
        Objectz obj = new Objectz();
        obj.setClazz(clazz);
        obj.setFields(new LocalVars(clazz.instanceSlotCount));
        return obj;
    }

    public boolean isAssignableFrom(Clazz other) {
        if (this.equals(other)) {
            return true;
        }
        if (!this.isInterface()) {
            return other.isSubClassOf(this);
        } else {
            return other.isImplements(this);
        }
    }

    public boolean isImplements(Clazz inter) {
        Clazz c = this;
        while (c != null) {
            for (Clazz clazz : c.getInterfaces()) {
                if (clazz.equals(inter) || clazz.isSubInterfaceOf(inter)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubInterfaceOf(Clazz inter) {
        for (Clazz superInter : this.getInterfaces()) {
            if (superInter.equals(inter) || superInter.isSubInterfaceOf(inter)) {
                return true;
            }
        }
        return false;
    }

    public Method getMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public Method getStaticMethod(String name, String descriptor) {
        for (Method method : this.getMethods()) {
            if (method.isStatic() && method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public static void initClass(Thread thread, Clazz clazz) {
        clazz.startInit();
        // TODO: 2016/10/20
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Clazz clazz) {
        Method clinitMethod = clazz.getClinitMethod();
        if (clinitMethod != null) {
            Frame newFrame = thread.newFrame(clinitMethod);
            thread.pushFrame(newFrame);
        }
    }

    private static void initSuperClass(Thread thread, Clazz clazz) {
        if (!clazz.isInterface()) {
            Clazz superClass = clazz.getSuperClass();
            if (superClass != null && !superClass.initStarted) {
                initClass(thread, superClass);
            }
        }
    }

    public Method getClinitMethod() {
        return this.getStaticMethod("<clinit>", "()V");
    }

    @Override
    public String toString() {
        return "{Class name: "+this.name+"}";
    }
}
