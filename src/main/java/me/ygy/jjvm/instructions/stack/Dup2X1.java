package me.ygy.jjvm.instructions.stack;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Dup2X1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        LocalVars.Slot v1 = stack.popSlot();
        LocalVars.Slot v2 = stack.popSlot();
        LocalVars.Slot v3 = stack.popSlot();
        LocalVars.Slot v1Dup = new LocalVars.Slot();
        LocalVars.Slot v2Dup = new LocalVars.Slot();
        v1Dup.num = v1.num;
        v1Dup.ref = v1.ref;
        v2Dup.num = v2.num;
        v2Dup.ref = v2.ref;
        stack.pushSlot(v2);
        stack.pushSlot(v1);
        stack.pushSlot(v3);
        stack.pushSlot(v2Dup);
        stack.pushSlot(v1Dup);
    }
}
