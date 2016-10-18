package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class InvokeSpecial extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        //todo not completed
        frame.getOperandStack().popRef();
    }
}
