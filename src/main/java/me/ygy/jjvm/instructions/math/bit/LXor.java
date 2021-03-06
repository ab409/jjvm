package me.ygy.jjvm.instructions.math.bit;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LXor extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long v1 = stack.popLong();
        long v2 = stack.popLong();
        long result = v1 ^ v2;
        stack.pushLong(result);
    }
}
