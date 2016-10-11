package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:27.
 */
public class ConstantDoubleInfo implements ConstantInfo{

    private double val;

    public double getVal() {
        return val;
    }

    @Override
    public void readInfo(ClassReader reader) {
        long l = reader.readUint64();
        this.val = Double.longBitsToDouble(l);
    }
}
