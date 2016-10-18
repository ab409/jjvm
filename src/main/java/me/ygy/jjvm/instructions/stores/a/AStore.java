package me.ygy.jjvm.instructions.stores.a;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class AStore extends Index8Instruction {

    public static void _astore(Frame frame, int index) {
        Objectz ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }

    @Override
    public void execute(Frame frame) {
        _astore(frame, this.index);
    }
}
