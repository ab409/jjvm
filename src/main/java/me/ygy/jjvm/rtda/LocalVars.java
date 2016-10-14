package me.ygy.jjvm.rtda;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class LocalVars {

    public static class Slot {
        int num;
        Object ref;
    }

    private Slot[] slots;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            slots = new Slot[maxLocals];
            for (int i = 0; i < maxLocals; i++) {
                this.slots[i] = new Slot();
            }
        }
    }

    public void setInt(int index, int val) {
        this.slots[index].num = val;
    }

    public int getInt(int index) {
        return this.slots[index].num;
    }

    public void setFloat(int index, float val) {
        int i = Float.floatToIntBits(val);
        this.slots[index].num = i;
    }

    public float getFloat(int index) {
        int num = this.slots[index].num;
        return Float.intBitsToFloat(num);
    }

    public void setLong(int index, long val) {
        this.slots[index].num = (int)val;
        this.slots[index+1].num = (int)(val >>> 32);
    }

    public long getLong(int index) {
        long low = this.slots[index].num;
        long high = this.slots[index + 1].num;
        return (high << 32) + low;
    }

    public void setDouble(int index, double val) {
        long bits = Double.doubleToLongBits(val);
        this.setLong(index, bits);
    }

    public double getDouble(int index) {
        long bits = this.getLong(index);
        return Double.longBitsToDouble(bits);
    }

    public void setRef(int index, Object ref) {
        this.slots[index].ref = ref;
    }

    public Object getRef(int index) {
        return this.slots[index].ref;
    }
}
