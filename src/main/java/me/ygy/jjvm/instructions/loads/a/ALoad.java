package me.ygy.jjvm.instructions.loads.a;

import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class ALoad extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        _aload(frame, this.index);
    }

    public static void _aload(Frame frame, int index) {
        Object ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }
}
