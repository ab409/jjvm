package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/21.
 */
public class ArrayLength extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Objectz arrRef = stack.popRef();
        if (arrRef == null) {
            throw new NullPointerException("array is null");
        }
        int arrLen = arrRef.arrayLength();
        stack.pushInt(arrLen);
    }
}
