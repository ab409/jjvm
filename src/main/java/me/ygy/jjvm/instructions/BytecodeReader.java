package me.ygy.jjvm.instructions;

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

    public byte readUint8() {
        byte b = this.code[this.pc];
        this.pc++;
        return b;
    }

    public int readInt8() {
        return (int)this.readUint8();
    }

    public int readUint16() {
        int b1 = this.readUint8();
        int b2 = this.readUint8();
        return (b1 << 8) + b2;
    }

    public int readInt16() {
        return this.readUint16();
    }

    public int readInt32() {
        int b1 = this.readUint8();
        int b2 = this.readUint8();
        int b3 = this.readUint8();
        int b4 = this.readUint8();
        return (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
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
