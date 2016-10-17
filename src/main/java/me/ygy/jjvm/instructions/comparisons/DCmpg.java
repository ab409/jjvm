package me.ygy.jjvm.instructions.comparisons;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class DCmpg extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        DCmpl._dcmp(frame, true);
    }
}
