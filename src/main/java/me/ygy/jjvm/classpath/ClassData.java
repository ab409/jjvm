package me.ygy.jjvm.classpath;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/10 11:36.
 */
public class ClassData {
    private byte[] data;
    private Entry entry;

    public ClassData(byte[] data, Entry entry) {
        this.data = data;
        this.entry = entry;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
