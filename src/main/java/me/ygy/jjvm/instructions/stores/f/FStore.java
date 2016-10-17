package me.ygy.jjvm.instructions.stores.f;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class FStore extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        _fstore(frame, this.index);
    }

    public static void _fstore(Frame frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
