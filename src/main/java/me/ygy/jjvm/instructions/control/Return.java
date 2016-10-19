package me.ygy.jjvm.instructions.control;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/19.
 */
public class Return extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}
