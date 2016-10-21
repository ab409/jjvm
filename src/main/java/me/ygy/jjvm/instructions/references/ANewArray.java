package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.ClassRef;
import me.ygy.jjvm.rtda.heap.Clazz;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/21.
 */
public class ANewArray extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        OperandStack stack = null;
        int count = 0;
        Clazz arrClass = null;
        try {
            Clazz componentClass = classRef.resolvedClass();
            stack = frame.getOperandStack();
            count = stack.popInt();
            if (count < 0) {
                throw new NegativeArraySizeException();
            }
            arrClass = componentClass.arrayClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objectz array = arrClass.newArray(count);
        stack.pushRef(array);
    }
}
