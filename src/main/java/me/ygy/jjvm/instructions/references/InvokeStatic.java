package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.instructions.base.InvokeMethod;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.heap.Clazz;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.Method;
import me.ygy.jjvm.rtda.heap.MethodRef;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/19.
 */
public class InvokeStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method method = null;
        try {
            method = methodRef.resolvedMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clazz clazz = method.getClazz();
        if (!clazz.isInitStarted()) {
            // TODO: 2016/10/20 init class
            frame.revertNextPc();
            Clazz.initClass(frame.getThread(), clazz);
            return;
        }
        if (!method.isStatic()) {
            throw new IncompatibleClassChangeError("method should be static");
        }
        InvokeMethod.invokeMethod(frame, method);
    }
}
