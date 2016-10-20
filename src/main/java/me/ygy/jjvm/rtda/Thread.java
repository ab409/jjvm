package me.ygy.jjvm.rtda;

import me.ygy.jjvm.rtda.heap.Method;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Thread {

    private int pc;
    private Stack stack;

    public Thread() {
        this.stack = new Stack(1024);
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }
    public Frame topFrame() {
        return this.stack.top();
    }

    public Frame currentFrame() {
        return this.stack.top();
    }

    public int getPc() {
        return pc;
    }

    public Stack getStack() {
        return stack;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public Frame newFrame(Method method) {
        return new Frame(method, this);
    }

    public boolean isStackEmpty() {
        return this.stack.isEmpty();
    }
}
