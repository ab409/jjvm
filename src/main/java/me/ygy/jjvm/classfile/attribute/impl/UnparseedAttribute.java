package me.ygy.jjvm.classfile.attribute.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.attribute.AttributeInfo;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 17:23.
 */
public class UnparseedAttribute implements AttributeInfo {

    private String name;
    private int length;
    private byte[] info;

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }
}
