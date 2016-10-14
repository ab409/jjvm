package me.ygy.jjvm.instructions.comparisons.ifcondition;

import me.ygy.jjvm.instructions.base.BranchInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class IfGt extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        if (val > 0) {
            super.branch(frame, this.offset);
        }
    }
}
