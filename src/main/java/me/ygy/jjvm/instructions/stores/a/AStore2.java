package me.ygy.jjvm.instructions.stores.a;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class AStore2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        AStore._astore(frame, 2);
    }
}
