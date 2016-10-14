package me.ygy.jjvm.instructions.base;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Index8Instruction implements Instruction {

    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {

    }
}
