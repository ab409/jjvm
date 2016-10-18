package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.*;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class PutField extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        Clazz currentClazz = currentMethod.getClazz();
        ConstantPool constantPool = currentClazz.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.getField();
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError(
                    String.format("%s' field %s is static", field.getClazz().getName(), field.getName()));
        }
        if (field.isFinal()) {
            if (!currentClazz.equals(field.getClazz()) ||
                    "<init>".equals(currentMethod.getName())) {
                throw new IllegalAccessError(
                        String.format("%s' field %s is not accessible", field.getClazz().getName(), field.getName()));
            }
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();
        Objectz ref;
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int i = stack.popInt();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("obj is null, can not set field to it");
                }
                ref.getFields().setInt(slotId, i);
                break;
            case 'F':
                float f = stack.popFloat();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("obj is null, can not set field to it");
                }
                ref.getFields().setFloat(slotId, f);
                break;
            case 'J':
                long l = stack.popLong();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("obj is null, can not set field to it");
                }
                ref.getFields().setLong(slotId, l);
                break;
            case 'D':
                double d = stack.popDouble();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("obj is null, can not set field to it");
                }
                ref.getFields().setDouble(slotId, d);
                break;
            case 'L':
            case '[':
                Objectz val = stack.popRef();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("obj is null, can not set field to it");
                }
                ref.getFields().setRef(slotId, val);
                break;
        }
    }
}
