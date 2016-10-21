package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.ClassLoader;
import me.ygy.jjvm.rtda.heap.Clazz;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/21.
 */
public class NewArray implements Instruction{

    private static final int AT_BOOLEAN    = 4;
    private static final int AT_CHAR       = 5;
    private static final int AT_FLOAT      = 6;
    private static final int AT_DOUBLE     = 7;
    private static final int AT_BYTE       = 8;
    private static final int AT_SHORT      = 9;
    private static final int AT_INT        = 10;
    private static final int AT_LONG       = 11;

    private int atype;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.atype = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        ClassLoader classLoader = frame.getMethod().getClazz().getClassLoader();
        Clazz arrClass = null;
        try {
            arrClass = getPrimitiveArrayClass(classLoader, this.atype);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objectz array = arrClass.newArray(count);
        stack.pushRef(array);
    }
    
    public Clazz getPrimitiveArrayClass(ClassLoader classLoader, int atype) throws IOException {
        switch (atype) {
            case AT_BOOLEAN: return classLoader.loadClass("[Z");
            case AT_BYTE: return classLoader.loadClass("[B");
            case AT_CHAR: return classLoader.loadClass("[C");
            case AT_SHORT: return classLoader.loadClass("[S");
            case AT_INT: return classLoader.loadClass("[I");
            case AT_LONG: return classLoader.loadClass("[J");
            case AT_FLOAT: return classLoader.loadClass("[F");
            case AT_DOUBLE: return classLoader.loadClass("[D");
            default:
                throw new IllegalArgumentException("invalid array type");
        }
    }
}
