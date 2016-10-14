package me.ygy.jjvm.instructions.loads.a;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class ALoad1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        ALoad._aload(frame, 1);
    }
}
