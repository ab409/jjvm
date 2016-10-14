package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class SIPush implements Instruction {

    private int val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.val = reader.readInt16();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(this.val);
    }
}
