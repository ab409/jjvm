package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.rtda.LocalVars;

import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Objectz {

    private Clazz clazz;
    private List<LocalVars.Slot> slots;

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public List<LocalVars.Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<LocalVars.Slot> slots) {
        this.slots = slots;
    }
}
