package me.ygy.jjvm.instructions.loads.d;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class DLoad extends Index8Instruction {

    public static void _dload(Frame frame, int index) {
        double d = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(d);
    }

    @Override
    public void execute(Frame frame) {
        DLoad._dload(frame, this.index);
    }
}
