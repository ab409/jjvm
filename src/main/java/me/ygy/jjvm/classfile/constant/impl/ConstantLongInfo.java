package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:26.
 */
public class ConstantLongInfo implements ConstantInfo {

    private long val;

    public long getVal() {
        return val;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64();
    }
}
