package me.ygy.jjvm.instructions.control;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.instructions.base.BranchInstruction;
import me.ygy.jjvm.rtda.Frame;

import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class TableSwitch extends BranchInstruction {

    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;


    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.low = reader.readInt32();
        this.high = reader.readInt32();
        int jumpOffsetCount = this.high - this.low + 1;
        this.jumpOffsets = reader.readInt32s(jumpOffsetCount);
    }

    @Override
    public void execute(Frame frame) {
        int index = frame.getOperandStack().popInt();
        int offset;
        if (index >= this.low && index <= this.high) {
            offset = this.jumpOffsets[index - this.low];
        } else {
            offset = this.defaultOffset;
        }
        super.branch(frame, offset);
    }
}
