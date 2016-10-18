package me.ygy.jjvm.rtda.heap;

import me.ygy.jjvm.classfile.MemberInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class Field extends ClassMember {

    public Field(MemberInfo memberInfo) {
        super(memberInfo);
    }

    public static List<Field> newFields(Clazz clazz, List<MemberInfo> memberInfos) {
        List<Field> list = new ArrayList<>(memberInfos.size());
        for (MemberInfo info : memberInfos) {
            Field field = new Field(info);
            field.setClazz(clazz);
            list.add(field);
        }
        return list;
    }
}
