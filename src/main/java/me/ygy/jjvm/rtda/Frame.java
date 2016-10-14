package me.ygy.jjvm.rtda;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Frame {

    private LocalVars localVars;
    private OperandStack operandStack;

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Frame(int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }
}
