package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.Clazz;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.Field;
import me.ygy.jjvm.rtda.heap.FieldRef;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class GetStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = null;
        try {
            field = fieldRef.resolvedField();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clazz clazz = field.getClazz();
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            Clazz.initClass(frame.getThread(), clazz);
            return;
        }
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError(
                    String.format("%s' field %s is not static", field.getClazz().getName(), field.getName()));
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars slots = clazz.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
        }
    }
}
