package me.ygy.jjvm.instructions.control;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.base.BranchInstruction;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LoopupSwitch extends BranchInstruction{

    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.npairs = reader.readInt32();
        this.matchOffsets = reader.readInt32s(this.npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        for (int i = 0; i < this.npairs * 2; i++) {
            if (this.matchOffsets[i] == key) {
                int offset = this.matchOffsets[i + 1];
                super.branch(frame, offset);
                return;
            }
        }
        super.branch(frame, this.defaultOffset);
    }
}
