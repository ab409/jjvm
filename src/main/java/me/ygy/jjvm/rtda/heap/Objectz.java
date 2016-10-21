package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.rtda.LocalVars;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Objectz {

    private Clazz clazz;
    private Object data;

    public Objectz(Clazz clazz) {
        this.clazz = clazz;
        this.data = new LocalVars.Slot[clazz.getInstanceSlotCount()];
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public LocalVars getFields() {
        return (LocalVars) data;
    }

    public void setFields(LocalVars fields) {
        this.data = fields;
    }

    public boolean isInstanceOf(Clazz clazz) {
        return clazz.isAssignableFrom(this.getClazz());
    }

    public byte[] bytes() {
        return (byte[]) this.data;
    }

    public short[] shorts() {
        return (short[]) this.data;
    }

    public int[] ints() {
        return (int[]) this.data;
    }

    public long[] longs() {
        return (long[]) this.data;
    }

    public char[] chars() {
        return (char[]) this.data;
    }

    public float[] floats() {
        return (float[]) this.data;
    }

    public double[] doubles() {
        return (double[]) this.data;
    }

    public Objectz[] refs() {
        return (Objectz[]) this.data;
    }

    public int arrayLength() {
        if (this.data instanceof byte[]) {
            return ((byte[]) this.data).length;
        } else if (this.data instanceof short[]) {
            return ((short[]) this.data).length;
        } else if (this.data instanceof int[]) {
            return ((int[]) this.data).length;
        } else if (this.data instanceof long[]) {
            return ((long[]) this.data).length;
        }else if (this.data instanceof char[]) {
            return ((char[]) this.data).length;
        } else if (this.data instanceof  float[]) {
            return ((float[]) this.data).length;
        } else if (this.data instanceof double[]) {
            return ((double[]) this.data).length;
        } else if (this.data instanceof Objectz[]) {
            return ((Objectz[]) this.data).length;
        } else {
            throw new IllegalArgumentException("object is not array");
        }
    }

    public Objectz(Clazz clazz, byte[] data) {
        this.clazz = clazz;
        this.data = data;;
    }
    public Objectz(Clazz clazz, short[] data) {
        this.clazz = clazz;
        this.data = data;;
    }
    public Objectz(Clazz clazz, int[] data) {
        this.clazz = clazz;
        this.data = data;;
    }
    public Objectz(Clazz clazz, long[] data) {
        this.clazz = clazz;
        this.data = data;;
    }

    public Objectz(Clazz clazz, char[] data) {
        this.clazz = clazz;
        this.data = data;;
    }
    public Objectz(Clazz clazz, float[] data) {
        this.clazz = clazz;
        this.data = data;;
    }
    public Objectz(Clazz clazz, double[] data) {
        this.clazz = clazz;
        this.data = data;;
    }
    public Objectz(Clazz clazz, Object[] data) {
        this.clazz = clazz;
        this.data = data;
    }

}
