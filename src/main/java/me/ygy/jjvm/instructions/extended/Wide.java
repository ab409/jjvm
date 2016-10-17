package me.ygy.jjvm.instructions.extended;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.instructions.control.Ret;
import me.ygy.jjvm.instructions.loads.a.ALoad;
import me.ygy.jjvm.instructions.loads.d.DLoad;
import me.ygy.jjvm.instructions.loads.f.FLoad;
import me.ygy.jjvm.instructions.loads.i.ILoad;
import me.ygy.jjvm.instructions.loads.l.LLoad;
import me.ygy.jjvm.instructions.math.inc.IInc;
import me.ygy.jjvm.instructions.stores.a.AStore;
import me.ygy.jjvm.instructions.stores.d.DStore;
import me.ygy.jjvm.instructions.stores.f.FStore;
import me.ygy.jjvm.instructions.stores.i.IStore;
import me.ygy.jjvm.instructions.stores.l.LStore;
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
                LLoad lLoad = new LLoad();
                lLoad.index = reader.readUint16();
                this.modifiedInstruction = lLoad;
                break;
            case 0x17:
                FLoad fLoad = new FLoad();
                fLoad.index = reader.readUint16();
                this.modifiedInstruction = fLoad;
                break;
            case 0x18:
                DLoad dLoad = new DLoad();
                dLoad.index = reader.readUint16();
                this.modifiedInstruction = dLoad;
                break;
            case 0x19:
                ALoad aLoad = new ALoad();
                aLoad.index = reader.readUint16();
                this.modifiedInstruction = aLoad;
                break;
            case 0x36:
                IStore iStore = new IStore();
                iStore.index = reader.readUint16();
                this.modifiedInstruction = iStore;
                break;
            case 0x37:
                LStore lStore = new LStore();
                lStore.index = reader.readUint16();
                this.modifiedInstruction = lStore;
                break;
            case 0x38:
                FStore fStore = new FStore();
                fStore.index = reader.readUint16();
                this.modifiedInstruction = fStore;
                break;
            case 0x39:
                DStore dStore = new DStore();
                dStore.index = reader.readUint16();
                this.modifiedInstruction = dStore;
                break;
            case 0x3a:
                AStore aStore = new AStore();
                aStore.index = reader.readUint16();
                this.modifiedInstruction = aStore;
                break;
            case 0xa9:
                Ret ret = new Ret();
                ret.index = reader.readUint16();
                this.modifiedInstruction = ret;
                break;
            case 0x84:
                IInc iInc = new IInc();
                iInc.index = reader.readUint16();
                iInc.constant = reader.readInt16();
                this.modifiedInstruction = iInc;
                break;

        }
    }

    @Override
    public void execute(Frame frame) {
        this.modifiedInstruction.execute(frame);
    }
}
