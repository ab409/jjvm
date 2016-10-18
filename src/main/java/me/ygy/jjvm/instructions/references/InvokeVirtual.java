package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.MethodRef;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class InvokeVirtual extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        if ("println".equals(methodRef.getName())) {
            OperandStack stack = frame.getOperandStack();
            switch (methodRef.getDescriptor()) {
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
                default:
                    throw new IllegalArgumentException("pringln: "+methodRef.getDescriptor());
            }
            stack.popRef();
        }
    }
}
