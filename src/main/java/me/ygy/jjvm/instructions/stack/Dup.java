package me.ygy.jjvm.instructions.stack;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Dup extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        LocalVars.Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
