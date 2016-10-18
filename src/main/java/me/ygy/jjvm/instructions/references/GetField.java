package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.Field;
import me.ygy.jjvm.rtda.heap.FieldRef;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class GetField extends Index16Instruction {

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
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError(
                    String.format("%s' field %s is static", field.getClazz().getName(), field.getName()));
        }
        OperandStack stack = frame.getOperandStack();
        Objectz ref = stack.popRef();
        if (ref == null) {
            throw new NullPointerException("");
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars slots = ref.getFields();
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
