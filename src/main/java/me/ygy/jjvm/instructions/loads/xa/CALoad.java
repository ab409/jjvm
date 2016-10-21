package me.ygy.jjvm.instructions.loads.xa;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class CALoad extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Objectz arrRef = stack.popRef();
        AALoad.checkNotNull(arrRef);
        char[] chars = arrRef.chars();
        AALoad.checkIndex(chars.length, index);
        stack.pushInt(chars[index]);
    }
}
