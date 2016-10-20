package me.ygy.jjvm.rtda;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class Stack {

    private final int maxSize;
    private int size;
    private LinkedList<Frame> list;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.list = new LinkedList<>();
    }

    public void push(Frame frame) {
        if (this.size >= this.maxSize)
            throw new StackOverflowError("thread local stack overflow");
        this.list.push(frame);
        this.size++;
    }

    public Frame pop() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        this.size--;
        return this.list.pop();
    }

    public Frame top() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        Frame item = this.list.pop();
        this.list.push(item);
        return item;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
