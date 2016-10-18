package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.*;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class PutStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        Clazz currentClazz = currentMethod.getClazz();
        ConstantPool constantPool = currentClazz.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = null;
        try {
            field = fieldRef.resolvedField();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clazz clazz = field.getClazz();

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError(
                    String.format("%s' field %s is not static", field.getClazz().getName(), field.getName()));
        }
        if (field.isFinal()) {
            if (!currentClazz.equals(clazz) || "<clinit>".equals(currentMethod.getName())) {
                throw new IllegalAccessError(
                        String.format("%s' field %s is not accessible", field.getClazz().getName(), field.getName()));
            }
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars staticVars = clazz.getStaticVars();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                staticVars.setInt(slotId, operandStack.popInt());
                break;
            case 'F':
                staticVars.setFloat(slotId, operandStack.popFloat());
                break;
            case 'J':
                staticVars.setLong(slotId, operandStack.popLong());
                break;
            case 'D':
                staticVars.setDouble(slotId, operandStack.popDouble());
                break;
            case 'L':
            case '[':
                staticVars.setRef(slotId, operandStack.popRef());
                break;
        }
    }
}
