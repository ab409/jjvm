package me.ygy.jjvm.instructions.stores.d;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class DStore0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        DStore._dstore(frame, 0);
    }
}
