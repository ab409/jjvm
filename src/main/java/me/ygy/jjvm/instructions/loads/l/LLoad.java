package me.ygy.jjvm.instructions.loads.l;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LLoad extends Index8Instruction {

    public static void _lload(Frame frame, int index) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }

    @Override
    public void execute(Frame frame) {
        _lload(frame, this.index);
    }
}
