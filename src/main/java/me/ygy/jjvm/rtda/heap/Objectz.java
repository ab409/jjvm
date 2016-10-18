package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.rtda.LocalVars;

import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Objectz {

    private Clazz clazz;
    private LocalVars fields;

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public LocalVars getFields() {
        return fields;
    }

    public void setFields(LocalVars fields) {
        this.fields = fields;
    }
}
