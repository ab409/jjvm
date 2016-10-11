package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:24.
 */
public class ConstantFloatInfo implements ConstantInfo {

    private float val;

    public float getVal() {
        return val;
    }

    @Override
    public void readInfo(ClassReader reader) {
        int i = reader.readUint32();
        this.val = Float.intBitsToFloat(i);
    }
}
