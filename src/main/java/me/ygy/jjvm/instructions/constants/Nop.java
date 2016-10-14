package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Nop implements Instruction {
    @Override
    public void fetchOperands(BytecodeReader reader) {
        //do nothing
    }

    @Override
    public void execute(Frame frame) {
        //do nothing
    }
}
