package me.ygy.jjvm.instructions.loads.l;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LLoad1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LLoad._lload(frame, 1);
    }
}
