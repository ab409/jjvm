package me.ygy.jjvm.instructions.base;

import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.LocalVars;
import me.ygy.jjvm.rtda.Thread;
import me.ygy.jjvm.rtda.heap.Method;

/**
 * Created by guangyuanyu on 2016/10/19.
 */
public class InvokeMethod {

    public static void invokeMethod(Frame frame, Method method) {
        Thread thread = frame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);
        //
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                LocalVars.Slot slot = frame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
        //hack
        if (method.isNative()) {
            if (method.getName().equals("registerNatives")) {
                thread.popFrame();
            } else {
                throw new IllegalArgumentException("native method is not implemented");
            }
        }
    }
}
