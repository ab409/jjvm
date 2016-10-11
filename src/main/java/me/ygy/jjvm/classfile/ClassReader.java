package me.ygy.jjvm.classfile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 11:07.
 */
public class ClassReader {

    byte[] data;
    int index = 0;

    public ClassReader(byte[] data) {
        this.data = data;
        this.index = 0;
    }

    public byte readUint8() {
        return this.data[index++];
    }

    public short readUint16() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        return buffer.getShort(0);
    }

    public int readUint32() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        return buffer.getInt(0);
    }

    public long readUint64() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        buffer.put(data[index++]);
        return buffer.getLong(0);
    }

    public short[] readUint16s() {
        short n = this.readUint16();
        short[] ret = new short[n];
        for (int i = 0; i < n; i++) {
            ret[i] = readUint16();
        }
        return ret;
    }

    public byte[] readBytes(int length) {
        byte[] ret = new byte[length];
        for (int i = 0; i < length; i++) {
            ret[i] = data[index++];
        }
        return ret;
    }
}
