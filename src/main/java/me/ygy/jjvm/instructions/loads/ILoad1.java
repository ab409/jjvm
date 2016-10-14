package me.ygy.jjvm.instructions.loads;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class ILoad1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        ILoad._iload(frame, 1);
    }
}
