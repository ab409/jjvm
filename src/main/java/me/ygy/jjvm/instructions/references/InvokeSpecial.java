package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.instructions.base.InvokeMethod;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.heap.*;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class InvokeSpecial extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Clazz currentClass = frame.getMethod().getClazz();
        ConstantPool constantPool = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Clazz resolvedClass = null;
        Method resolvedMethod = null;
        try {
            resolvedClass = methodRef.resolvedClass();
            resolvedMethod = methodRef.resolvedMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resolvedMethod.getName().equals("<init>") && !resolvedMethod.getClazz().equals(resolvedClass)) {
            throw new NoSuchMethodError(
                    String.format("can not found a method %s in class %s", resolvedMethod.getName(), resolvedClass.getName()));
        }
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError("method should not be static");
        }
        Objectz ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new NullPointerException(
                    String.format("null pointer, can not found a method %s in class %s", resolvedMethod.getName(), resolvedClass.getName()));
        }
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                !ref.getClazz().equals(currentClass) &&
                !ref.getClazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError(
                    String.format("can not access a method %s in class %s from %s", resolvedMethod.getName(), resolvedClass.getName(), currentClass.getName()));
        }
        Method methodToBeInvoked = resolvedMethod;
        if (currentClass.isSuper() &&
                resolvedClass.isSuperClassOf(currentClass) &&
                !resolvedMethod.getName().equals("<init>")) {
            methodToBeInvoked = Method.lookupMethodInClass(currentClass.getSuperClass(), methodRef.getName(), methodRef.getDescriptor());
        }
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError(
                    String.format("method %s in class %s is abstract", resolvedMethod.getName(), resolvedClass.getName()));
        }
        InvokeMethod.invokeMethod(frame, methodToBeInvoked);
    }
}
