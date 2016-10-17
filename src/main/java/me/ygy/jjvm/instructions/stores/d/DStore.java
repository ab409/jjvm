package me.ygy.jjvm.instructions.stores.d;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class DStore extends Index8Instruction {

    public static void _dstore(Frame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

    @Override
    public void execute(Frame frame) {
        _dstore(frame, this.index);
    }
}
