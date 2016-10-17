package me.ygy.jjvm.instructions.comparisons;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.OperandStack;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class DCmpl extends NoOperandsInstruction {

    public static void _dcmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        if (v1 > v2) {
            stack.pushInt(1);
        } else if (v1 == v2) {
            stack.pushInt(0);
        } else if (v1 < v2) {
            stack.pushInt(-1);
        } else if (gFlag) {
            stack.pushInt(1);
        } else {
            stack.pushInt(-1);
        }
    }

    @Override
    public void execute(Frame frame) {
        _dcmp(frame, false);
    }
}
