package me.ygy.jjvm.instructions.extended;

import me.ygy.jjvm.instructions.base.BranchInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class IfNonNull extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        Object ref = frame.getOperandStack().popRef();
        if (ref != null) {
            branch(frame, this.offset);
        }
    }
}
