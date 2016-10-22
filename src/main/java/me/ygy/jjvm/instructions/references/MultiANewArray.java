package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
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
public class MultiANewArray implements Instruction {

    private int index;
    private int dimensions;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint16();
        this.dimensions = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        OperandStack stack = null;
        Objectz arr = null;
        try {
            Clazz arrClass = classRef.resolvedClass();
            stack = frame.getOperandStack();
            int[] counts = popAndCheckCounts(stack, this.dimensions);
            arr = newMultiDimensionalArray(counts, arrClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stack.pushRef(arr);
    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];

        for (int i = dimensions - 1; i >= 0; i--) {
            int count = stack.popInt();
            if (count < 0) {
                throw new NegativeArraySizeException();
            }
            counts[i] = count;
        }
        return counts;
    }

    private Objectz newMultiDimensionalArray(int[] counts, Clazz arrClass) throws IOException {
        int count = counts[0];
        Objectz array = arrClass.newArray(count);
        if (counts.length > 1) {
            Objectz[] refs = array.refs();
            for (int i = 0; i < refs.length; i++) {
                int[] nextCounts = new int[counts.length - 1];
                for (int j = 0; j < nextCounts.length; j++) {
                    nextCounts[j] = counts[j+1];
                }
                refs[i] = newMultiDimensionalArray(nextCounts, arrClass.componentClass());
            }
        }
        return array;
    }
}
