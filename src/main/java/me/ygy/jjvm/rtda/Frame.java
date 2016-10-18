package me.ygy.jjvm.rtda;

import me.ygy.jjvm.rtda.heap.Method;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Frame {

    private Method method;
    private LocalVars localVars;
    private OperandStack operandStack;
    private me.ygy.jjvm.rtda.Thread thread;
    private int nextPc;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Thread getThread() {
        return thread;
    }

    public int getNextPc() {
        return nextPc;
    }

    public Frame(int maxLocals, int maxStack, me.ygy.jjvm.rtda.Thread thread) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.thread = thread;
        this.nextPc = 0;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }
}
