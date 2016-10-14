package me.ygy.jjvm.instructions.math;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class IInc implements Instruction {

    private int index;
    private int constant;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint8();
        this.constant = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars vars = frame.getLocalVars();
        int val = vars.getInt(this.index);
        val += this.constant;
        vars.setInt(this.index, val);
    }
}
