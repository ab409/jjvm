package me.ygy.jjvm.instructions.loads.f;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class FLoad extends Index8Instruction {

    public static void _fload(Frame frame, int index) {
        float f = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(f);
    }

    @Override
    public void execute(Frame frame) {
        _fload(frame, this.index);
    }
}
