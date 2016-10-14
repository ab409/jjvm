package me.ygy.jjvm.instructions.loads.f;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Fload2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        FLoad._fload(frame, 2);
    }
}
