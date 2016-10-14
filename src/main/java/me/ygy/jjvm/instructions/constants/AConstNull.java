package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class AConstNull extends NoOperandsInstruction{
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(null);
    }
}
