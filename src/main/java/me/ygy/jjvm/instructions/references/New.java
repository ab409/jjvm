package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.base.Index16Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.Thread;
import me.ygy.jjvm.rtda.heap.ClassRef;
import me.ygy.jjvm.rtda.heap.Clazz;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class New extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef ref = (ClassRef) constantPool.getConstant(this.index);
        Clazz clazz = null;
        try {
            clazz = ref.resolvedClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            Clazz.initClass(frame.getThread(), clazz);
            return;
        }
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError(String.format("%s is interface or abstract, so it can not be instanced", clazz.getName()));
        }
        Objectz obj = clazz.newObject();
        frame.getOperandStack().pushRef(obj);
    }
}
