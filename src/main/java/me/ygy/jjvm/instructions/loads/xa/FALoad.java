package me.ygy.jjvm.instructions.loads.xa;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class FALoad extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Objectz arrRef = stack.popRef();
        AALoad.checkNotNull(arrRef);
        float[] floats = arrRef.floats();
        AALoad.checkIndex(floats.length, index);
        stack.pushFloat(floats[index]);
    }
}
