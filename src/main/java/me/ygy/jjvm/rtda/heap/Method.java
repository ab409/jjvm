package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.MemberInfo;
import me.ygy.jjvm.classfile.attribute.impl.CodeAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Method extends ClassMember {

    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private int argSlotCount;

    public int getArgSlotCount() {
        return argSlotCount;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public Method(MemberInfo memberInfo) {
        super(memberInfo);
        copyAttributes(memberInfo);
    }

    public void copyAttributes(MemberInfo info) {
        CodeAttribute codeAttribute = info.getCodeAttribute();
        if (codeAttribute != null) {
            this.maxLocals = codeAttribute.getMaxLocals();
            this.maxStack = codeAttribute.getMaxStack();
            this.code = codeAttribute.getCode();
        }
    }

    public void calcArgSlotCount() {
        MethodDescriptor methodDescriptor = MethodDescriptor.parseMethodDescriptor(this.descriptor);
        for (String paramType : methodDescriptor.getParameterTypes()) {
            this.argSlotCount++;
            if (paramType.equals("J") || paramType.equals("D")) {
                this.argSlotCount++;
            }
        }
        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }

    public static List<Method> newMethods(Clazz clazz, List<MemberInfo> memberInfos) {
        List<Method> list = new ArrayList<>(memberInfos.size());
        for (MemberInfo info : memberInfos) {
            Method method = new Method(info);
            method.setClazz(clazz);
            method.calcArgSlotCount();
            list.add(method);
        }
        return list;
    }

    public static Method lookupMethod(Clazz cl, String name, String descriptor) {
        Method method = lookupMethodInClass(cl, name, descriptor);
        if (method == null) {
            method = lookupMethodInInterfaces(cl.getInterfaces(), name, descriptor);
        }
        return method;
    }

    public static Method lookupMethodInClass(Clazz clazz, String name, String descriptor) {
        Clazz c = clazz;
        while (c != null) {
            for (Method method : c.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
            c = c.getSuperClass();
        }
        return null;
    }

    public static Method lookupMethodInInterfaces(List<Clazz> ifaces, String name, String descriptor) {
        for (Clazz iface : ifaces) {
            for (Method method : iface.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
            Method method = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }

    public static Method lookupInterfaceMethod(Clazz iface, String name, String descriptor) {
        for (Method method : iface.getMethods()) {
            if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        return lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
    }
}
