package me.ygy.jjvm.instructions.stores;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LStore extends Index8Instruction {

    public static void _lstore(Frame frame, int index) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }

    @Override
    public void execute(Frame frame) {
        _lstore(frame, this.index);
    }
}
