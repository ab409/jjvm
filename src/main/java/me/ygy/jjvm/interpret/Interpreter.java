package me.ygy.jjvm.interpret;

import me.ygy.jjvm.classfile.MemberInfo;
import me.ygy.jjvm.classfile.attribute.impl.CodeAttribute;
import me.ygy.jjvm.instructions.BytecodeReader;
import me.ygy.jjvm.instructions.Instruction;
import me.ygy.jjvm.rtda.Frame;
import me.ygy.jjvm.rtda.Thread;
import me.ygy.jjvm.rtda.heap.Method;

/**
 * Created by guangyuanyu on 2016/10/17.
 */
public class Interpreter {

    public static void interpret(Method method) {
        me.ygy.jjvm.rtda.Thread thread = new me.ygy.jjvm.rtda.Thread();
        Frame frame = thread.newFrame(method);
        try {
            loop(thread, method.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("LocalVars:%s\n", frame.getLocalVars().toString()));
            System.out.println(String.format("OperandStack:%s\n", frame.getOperandStack().toString()));
        }
    }

    public static void loop(me.ygy.jjvm.rtda.Thread thread, byte[] bytecode) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            int pc = frame.getNextPc();
            thread.setPc(pc);
            reader.reset(bytecode, pc);
            int opcode = reader.readUint8();
            Instruction instruction = Instruction.newInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPc(reader.getPc());

            //execute
            System.out.println(String.format("pc:%2d inst %s", pc, instruction.getClass().toString()));
            instruction.execute(frame);
        }
    }
}
