package me.ygy.jjvm.instructions.math;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LShL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        long v1 = stack.popLong();
        int s = v2 & 0x3F;
        long result = v1 << s;
        stack.pushLong(result);
    }
}
