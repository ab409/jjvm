package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class LdcW extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Ldc._ldc(frame, this.index);
    }
}
