package me.ygy.jjvm.classfile.constant.impl;

import me.ygy.jjvm.classfile.ClassReader;
import me.ygy.jjvm.classfile.constant.ConstantInfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Author: guangyuanyu
 * Email: guangyuanyu@sohu-inc.com
 * Date: 2016/10/11 16:28.
 */
public class ConstantUtf8Info implements ConstantInfo {

    String str;

    public String getStr() {
        return str;
    }

    @Override
    public void readInfo(ClassReader reader) {
        short length = reader.readUint16();
        byte[] bytes = reader.readBytes((int) length);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            this.str = in.toString();
            in.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("constant utf8 init failed, class file is not valid");
        }
    }
}
