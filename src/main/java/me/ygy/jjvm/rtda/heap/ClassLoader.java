package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.ClassFile;
import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.Classpath;
import me.ygy.jjvm.rtda.LocalVars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class ClassLoader {

    private Classpath classpath;
    private Map<String, Clazz> classMap;
    private boolean verboseFlag;

    public ClassLoader(Classpath classpath, boolean verboseClassFlag) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public Classpath getClasspath() {
        return classpath;
    }

    public Map<String, Clazz> getClassMap() {
        return classMap;
    }

    public Clazz loadClass(String name) throws IOException {
        if (this.classMap.containsKey(name)) {
            return this.classMap.get(name);
        }
        return this.loadNonArrayClass(name);
    }

    private Clazz loadNonArrayClass(String name) throws IOException {
        // defineclass linkclass
        ClassData classData = this.readClass(name);
        Clazz clazz = this.defineClass(classData.getData());
        this.link(clazz);
        if (this.verboseFlag) {
            System.out.println(String.format("[Loaded %s from %s]", name, classData.getEntry()));
        }
        return clazz;
    }

    private ClassData readClass(String name) throws IOException {
        ClassData classData = this.classpath.readClass(name);
        return classData;
    }

    private Clazz defineClass(byte[] data) throws IOException {
        Clazz clazz = parseClass(data);
        clazz.setClassLoader(this);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.getName(), clazz);
        return clazz;
    }

    private Clazz parseClass(byte[] data) {
        ClassFile classFile = new ClassFile(data);
        return new Clazz(classFile);
    }

    private void resolveSuperClass(Clazz clazz) throws IOException {
        if (!"java/lang/Object".equals(clazz.getName())) {
            clazz.setSuperClass(clazz.getClassLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    private void resolveInterfaces(Clazz clazz) throws IOException {
        int interfaceCount = clazz.getInterfaceNames().length;
        if (interfaceCount > 0) {
            clazz.setInterfaces(new ArrayList<>(interfaceCount));
            for (String interfaceName : clazz.getInterfaceNames()) {
                clazz.getInterfaces().add(clazz.getClassLoader().loadClass(interfaceName));
            }
        }
    }

    private void link(Clazz clazz) {
        this.verify(clazz);
        this.prepare(clazz);
    }

    private void verify(Clazz clazz) {
        // todo will not implement this method
    }

    private void prepare(Clazz clazz) {
        // prepare
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    private void calcInstanceFieldSlotIds(Clazz clazz) {
        int slotId = 0;
        if (clazz.getSuperClass() != null) {
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }
        for (Field field : clazz.getFields()) {
            if (!field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.setInstanceSlotCount(slotId);
    }

    private void calcStaticFieldSlotIds(Clazz clazz) {
        int slotId = 0;
        for (Field field : clazz.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.setStaticSlotCount(slotId);
    }

    private void allocAndInitStaticVars(Clazz clazz) {
        clazz.setStaticVars(new LocalVars(clazz.getStaticSlotCount()));
        for (Field field : clazz.getFields()) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private void initStaticFinalVar(Clazz clazz, Field field) {
        LocalVars staticVars = clazz.getStaticVars();
        ConstantPool constantPool = clazz.getConstantPool();
        int constValueIndex = field.getConstValueIndex();
        int slotId = field.getSlotId();
        Object constant;
        if (constValueIndex > 0) {
            switch (field.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    constant = constantPool.getConstant(constValueIndex);
                    staticVars.setInt(slotId, Integer.valueOf(constant.toString()));
                    break;
                case "J":
                    constant = constantPool.getConstant(constValueIndex);
                    staticVars.setLong(slotId, Long.valueOf(constant.toString()));
                    break;
                case "F":
                    constant = constantPool.getConstant(constValueIndex);
                    staticVars.setFloat(slotId, Float.valueOf(constant.toString()));
                    break;
                case "D":
                    constant = constantPool.getConstant(constValueIndex);
                    staticVars.setDouble(slotId, Double.valueOf(constant.toString()));
                    break;
                case "Ljava/lang.String;":
                    //todo string implement
                    throw new IllegalArgumentException("todo string");
            }
        }
    }
}
