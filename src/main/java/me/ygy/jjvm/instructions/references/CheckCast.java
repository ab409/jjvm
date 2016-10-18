package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.ClassRef;
import me.ygy.jjvm.rtda.heap.Clazz;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class CheckCast extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Objectz ref = stack.popRef();
        stack.pushRef(ref);
        if (ref == null) {
            return;
        }
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        Clazz clazz = null;
        try {
            clazz = classRef.resolvedClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!ref.isInstanceOf(clazz)) {
            throw new ClassCastException(String.format("%s is not a %s", ref.getClazz().getName(), clazz.getName()));
        }
    }
}
