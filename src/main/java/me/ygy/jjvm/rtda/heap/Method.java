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

    public static List<Method> newMethods(Clazz clazz, List<MemberInfo> memberInfos) {
        List<Method> list = new ArrayList<>(memberInfos.size());
        for (MemberInfo info : memberInfos) {
            Method method = new Method(info);
            method.setClazz(clazz);
            list.add(method);
        }
        return list;
    }
}
