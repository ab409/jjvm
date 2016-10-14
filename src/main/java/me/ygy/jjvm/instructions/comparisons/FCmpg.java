package me.ygy.jjvm.instructions.comparisons;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class FCmpg extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        FCmpl._fcmp(frame, true);
    }
}
