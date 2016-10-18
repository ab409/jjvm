package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.ConstantPool;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Ldc2W extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Object c = constantPool.getConstant(this.index);
        if (c instanceof Long) {
            stack.pushLong(((Long) c).longValue());
        } else if (c instanceof Double) {
            stack.pushDouble(((Double) c).doubleValue());
        } else {
            throw new ClassFormatError("ldc2W instruction type invalid");
        }
    }
}
