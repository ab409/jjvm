package me.ygy.jjvm.instructions.loads.l;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LLoad3 extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        LLoad._lload(frame, 3);
    }
}
