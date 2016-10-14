package me.ygy.jjvm.instructions.loads;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class ILoad extends Index8Instruction {

    public static void _iload(Frame frame, int index) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }

    @Override
    public void execute(Frame frame) {
        _iload(frame, this.index);
    }
}
