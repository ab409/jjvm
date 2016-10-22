package me.ygy.jjvm.instructions.constants;

import me.ygy.jjvm.classfile.constant.impl.ConstantIntegerInfo;
import me.ygy.jjvm.instructions.base.Index8Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.*;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Ldc extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        _ldc(frame, this.index);
    }

    public static void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        Clazz clazz = frame.getMethod().getClazz();
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Object c = constantPool.getConstant(index);
        if (c instanceof Integer) {
            stack.pushInt(((Integer) c).intValue());
        } else if (c instanceof Float) {
            stack.pushFloat(((Float) c).floatValue());
        } else if (c instanceof String) {
            Objectz str = InternedStrings.jString(clazz.getClassLoader(), (String) c);
            stack.pushRef(str);
        } else if (c instanceof ClassRef) {
            //todo
        } else {
            //todo ldc
            throw new IllegalArgumentException("ldc not implemented");
        }
    }
}
