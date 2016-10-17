package me.ygy.jjvm.instructions.stores.i;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class IStore extends Index8Instruction{

    @Override
    public void execute(Frame frame) {
        _istore(frame, this.index);
    }

    public static void _istore(Frame frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
