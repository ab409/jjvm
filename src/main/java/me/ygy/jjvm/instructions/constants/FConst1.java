package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class FConst1 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(1.0f);
    }
}
