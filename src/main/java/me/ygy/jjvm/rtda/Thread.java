package me.ygy.jjvm.rtda;

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

    public Frame currentFrame() {
        return this.stack.top();
    }
}
