package me.ygy.jjvm.instructions.comparisons.ifacmp;

import me.ygy.jjvm.instructions.base.BranchInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class IfACmpEq extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref2 = stack.popRef();
        Object ref1 = stack.popRef();
        if (ref1 == ref2) {
            super.branch(frame, this.offset);
        }
    }
}
