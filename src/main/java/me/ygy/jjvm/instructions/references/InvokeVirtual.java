package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.instructions.base.InvokeMethod;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.*;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class InvokeVirtual extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Clazz currentClass = frame.getMethod().getClazz();
        ConstantPool constantPool = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method resolvedMethod = null;
        try {
            resolvedMethod = methodRef.resolvedMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError("method should not be static");
        }
        Objectz ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            // TODO: 2016/10/19  hack println
            if (methodRef.getName().equals("println")) {
                _println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            throw new NullPointerException(
                    String.format("null pointer,a method %s", resolvedMethod.getName()));
        }
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                !ref.getClazz().equals(currentClass) &&
                !ref.getClazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError(
                    String.format("can not access a method %s from %s", resolvedMethod.getName(), currentClass.getName()));
        }
        Method methodToBeInvoked = Method.lookupMethodInClass(ref.getClazz(), methodRef.getName(), methodRef.getDescriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError(
                    String.format("method %s is abstract", resolvedMethod.getName()));
        }
        InvokeMethod.invokeMethod(frame, methodToBeInvoked);
    }

    private void _println(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.out.println(""+(stack.popInt() != 0));
                break;
            case "(C)V":
                System.out.println(""+stack.popInt());
                break;
            case "(B)V":
                System.out.println(""+stack.popInt());
                break;
            case "(S)V":
                System.out.println(""+stack.popInt());
                break;
            case "(I)V":
                System.out.println(""+stack.popInt());
                break;
            case "(J)V":
                System.out.println(""+stack.popLong());
                break;
            case "(F)V":
                System.out.println(""+stack.popFloat());
                break;
            case "(D)V":
                System.out.println(""+stack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                Objectz jStr = stack.popRef();
                System.out.println(InternedStrings.string(jStr));
                break;
            default:
                throw new IllegalArgumentException("pringln: "+descriptor);
        }
        stack.popRef();
    }
}
