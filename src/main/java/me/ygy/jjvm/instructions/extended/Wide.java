package me.ygy.jjvm.instructions.extended;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.instructions.loads.i.ILoad;
import me.ygy.jjvm.rtda.Frame;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Wide implements Instruction {

    private Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        int opcode = reader.readUint8();
        switch (opcode) {
            case 0x15:
                ILoad inst = new ILoad();
                inst.index = reader.readUint16();
                this.modifiedInstruction = inst;
                break;
            case 0x16:
                break;
            case 0x17:
                break;
            case 0x18:
                break;
            case 0x19:
                break;
            case 0x36:
                break;
            case 0x37:
                break;
            case 0x38:
                break;
            case 0x39:
                break;
            case 0x3a:
                break;
            case 0xa9:
                break;
            case 0x84:
                break;

        }
    }

    @Override
    public void execute(Frame frame) {

    }
}
