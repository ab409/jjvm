package me.ygy.jjvm.instructions.loads.i;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class ILoad2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        ILoad._iload(frame, 2);
    }
}
