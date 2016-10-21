package me.ygy.jjvm.instructions.loads.xa;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.sql.Ref;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class AALoad extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Objectz arrRef = stack.popRef();
        checkNotNull(arrRef);
        Objectz[] refs = arrRef.refs();
        checkIndex(refs.length, index);
        stack.pushRef(refs[index]);
    }

    public static void checkNotNull(Objectz ref) {
        if (ref == null) {
            throw new NullPointerException();
        }
    }

    public static void checkIndex(int arrLen, int index) {
        if (index < 0 || index >= arrLen) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
