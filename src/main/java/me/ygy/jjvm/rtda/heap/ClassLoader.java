package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.ClassFile;
import me.ygy.jjvm.classpath.ClassData;
import me.ygy.jjvm.classpath.Classpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class ClassLoader {

    private Classpath classpath;
    private Map<String, Clazz> classMap;

    public ClassLoader(Classpath classpath) {
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
        // todo defineclass linkclass
        ClassData classData = this.readClass(name);
        Clazz clazz = this.defineClass(classData.getData());
        this.link(clazz);
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
        int interfaceCount = clazz.getInterfaces().size();
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
        // todo prepare
    }
}
