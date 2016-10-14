package me.ygy.jjvm.instructions;

import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public interface Instruction {
    //todo BytecodeReader
    void fetchOperands(BytecodeReader reader);
    void execute(Frame frame);
}
