package me.ygy.jjvm.instructions.stores.i;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class IStore0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        IStore._istore(frame, 0);
    }
}
