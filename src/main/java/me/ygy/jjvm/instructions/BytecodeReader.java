package me.ygy.jjvm.instructions;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class BytecodeReader {
    private byte[] code;
    private int pc;

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public byte[] getCode() {
        return code;
    }

    public int getPc() {
        return pc;
    }

    public int readUint8() {
        if (this.pc < 0 || this.pc >= this.code.length) {
            throw new ArrayIndexOutOfBoundsException("bytecode reader index invalid, pc="+this.pc+" code.length="+this.code.length);
        }
        byte b = this.code[this.pc];
        this.pc++;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)0);
        buffer.put((byte)0);
        buffer.put((byte)0);
        buffer.put(b);
        return buffer.getInt(0);
    }

    public int readInt8() {
        return this.readUint8();
    }

    public int readUint16() {
        byte b1 = this.code[this.pc];
        this.pc++;
        byte b2 = this.code[this.pc];
        this.pc++;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put((byte)0);
        buffer.put((byte)0);
        buffer.put(b1);
        buffer.put(b2);
        return buffer.getInt(0);
    }

    public int readInt16() {
        byte b1 = this.code[this.pc];
        this.pc++;
        byte b2 = this.code[this.pc];
        this.pc++;
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(b1);
        buffer.put(b2);
        return buffer.getShort(0);
    }

    public int readInt32() {
        byte b1 = this.code[this.pc];
        this.pc++;
        byte b2 = this.code[this.pc];
        this.pc++;
        byte b3 = this.code[this.pc];
        this.pc++;
        byte b4 = this.code[this.pc];
        this.pc++;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(b1);
        buffer.put(b2);
        buffer.put(b3);
        buffer.put(b4);
        return buffer.getInt(0);
    }

    public void skipPadding() {
        while (this.pc % 4 != 0) {
            this.readUint8();
        }
    }

    public int[] readInt32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = this.readInt32();
        }
        return ints;
    }
}
