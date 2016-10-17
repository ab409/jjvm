package me.ygy.jjvm.instructions.stores.f;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class FStore0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        FStore._fstore(frame, 0);
    }
}
