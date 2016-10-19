package me.ygy.jjvm.instructions.control;

import me.ygy.jjvm.instructions.base.NoOperandsInstruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.Thread;

/**
 * Created by guangyuanyu on 2016/10/19.
 */
public class IReturn extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        int retVal = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(retVal);
    }
}
