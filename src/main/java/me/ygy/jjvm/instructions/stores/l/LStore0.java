package me.ygy.jjvm.instructions.stores.l;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LStore0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        LStore._lstore(frame, 0);
    }
}
