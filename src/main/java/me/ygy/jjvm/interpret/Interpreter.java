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

    public static void interpret(Method method, boolean logInst) {
        me.ygy.jjvm.rtda.Thread thread = new me.ygy.jjvm.rtda.Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        try {
            loop(thread, logInst);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("LocalVars:%s\n", frame.getLocalVars().toString()));
            System.out.println(String.format("OperandStack:%s\n", frame.getOperandStack().toString()));
        }
    }

    public static void loop(me.ygy.jjvm.rtda.Thread thread, boolean logInst) {
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);
            reader.reset(frame.getMethod().getCode(), pc);
            int opcode = reader.readUint8();
            Instruction instruction = Instruction.newInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPc(reader.getPc());
            if (logInst) {
                logInstruction(frame, instruction);
            }
            //execute
            System.out.println(String.format("%s pc:%2d inst %s", frame.toString().substring(frame.toString().lastIndexOf(".")+1),
                    pc, instruction.getClass().toString().substring(instruction.getClass().toString().lastIndexOf(".")+1)));
            instruction.execute(frame);
            if (thread.isStackEmpty()) {
                break;
            }
        }
    }

    private static void logInstruction(Frame frame, Instruction inst) {
        Method method = frame.getMethod();
        String className = method.getClazz().getName();
        String methodName = method.getName();
        int pc = frame.getThread().getPc();
        System.out.println(String.format("%s.%s #%2d %s %s", className, methodName, pc, inst.getClass().toString(), inst.toString()));
    }
}
