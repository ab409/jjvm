package me.ygy.jjvm.instructions.math.bit;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class IXor extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v1 = stack.popInt();
        int v2 = stack.popInt();
        int result = v1 ^ v2;
        stack.pushInt(result);
    }
}
