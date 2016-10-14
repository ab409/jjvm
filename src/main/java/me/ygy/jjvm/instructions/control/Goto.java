package me.ygy.jjvm.instructions.control;

import me.ygy.jjvm.instructions.base.BranchInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Goto extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        super.branch(frame, this.offset);
    }
}
