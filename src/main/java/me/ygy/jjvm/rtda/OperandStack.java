package me.ygy.jjvm.rtda;

/**
 * Created by guangyuanyu on 2016/10/14.
 */
public class OperandStack {

    private int size;
    private LocalVars.Slot[] slots;

    public OperandStack(int maxStack) {
        this.size = 0;
        if (maxStack > 0) {
            this.slots = new LocalVars.Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                this.slots[i] = new LocalVars.Slot();
            }
        }
    }

    public void pushInt(int val) {
        this.slots[this.size].num = val;
        this.size++;
    }

    public int popInt() {
        this.size--;
        return this.slots[this.size].num;
    }

    public void pushFloat(float val) {
        int bits = Float.floatToIntBits(val);
        this.slots[this.size].num = bits;
        this.size++;
    }

    public float popFloat() {
        this.size--;
        return Float.intBitsToFloat(this.slots[this.size].num);
    }

    public void pushLong(long val) {
        this.slots[this.size].num = (int)val;
        this.slots[this.size+1].num = (int)(val >>> 32);
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        long low = this.slots[this.size].num;
        long high = this.slots[this.size + 1].num;
        return (high << 32) + (low);
    }

    public void pushDouble(double val) {
        long bits = Double.doubleToLongBits(val);
        this.pushLong(bits);
    }

    public double popDouble() {
        long bits = this.popLong();
        return Double.longBitsToDouble(bits);
    }

    public void pushRef(Object ref) {
        this.slots[this.size].ref = ref;
        this.size++;
    }

    public Object popRef() {
        this.size--;
        Object ref = this.slots[this.size].ref;
        this.slots[this.size].ref = null;
        return ref;
    }

    public void pushSlot(LocalVars.Slot slot) {
        this.slots[this.size] = slot;
        this.size++;
    }

    public LocalVars.Slot popSlot() {
        this.size--;
        return this.slots[this.size];
    }
}
