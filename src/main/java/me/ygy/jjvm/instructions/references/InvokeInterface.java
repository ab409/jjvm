package me.ygy.jjvm.instructions.references;

import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.instructions.base.InvokeMethod;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.heap.ConstantPool;
import me.ygy.jjvm.rtda.heap.InterfaceMethodRef;
import me.ygy.jjvm.rtda.heap.Method;
import me.ygy.jjvm.rtda.heap.Objectz;

import java.io.IOException;

/**
 * Created by guangyuanyu on 2016/10/19.
 */
public class InvokeInterface implements Instruction {

    private int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint16();
        reader.readUint8();
        reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) constantPool.getConstant(this.index);
        Method method = null;
        try {
            method = methodRef.resolvedInterfaceMethod();
            if (method.isStatic() || method.isPrivate()) {
                throw new IncompatibleClassChangeError("inerface method should not be static or private");
            }
            Objectz ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount() - 1);
            if (ref == null) {
                // TODO: 2016/10/20  
                throw new NullPointerException("");
            }
            if (!ref.getClazz().isImplements(methodRef.resolvedClass())) {
                throw new IncompatibleClassChangeError("class should implement interface");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        InvokeMethod.invokeMethod(frame, method);
    }
}
