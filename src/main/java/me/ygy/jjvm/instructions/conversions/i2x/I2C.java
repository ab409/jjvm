package me.ygy.jjvm.instructions.conversions.i2x;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class I2C extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Integer i = stack.popInt();
        stack.pushInt(i.shortValue());
    }
}
