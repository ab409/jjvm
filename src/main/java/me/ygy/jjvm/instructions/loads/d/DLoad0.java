package me.ygy.jjvm.instructions.loads.d;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class DLoad0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        DLoad._dload(frame, 0);
    }
}
