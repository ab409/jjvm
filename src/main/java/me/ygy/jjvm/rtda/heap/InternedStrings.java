package me.ygy.jjvm.rtda.heap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guangyuanyu on 2016/10/22.
 */
public class InternedStrings {

    private static Map<String, Objectz> map = new HashMap<>();

    public Map<String, Objectz> getMap() {
        return map;
    }

    public void setMap(Map<String, Objectz> map) {
        this.map = map;
    }

    public static Objectz jString(ClassLoader classLoader, String str) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        Objectz jChars = null;
        Objectz jStr = null;
        try {
            jChars = new Objectz(classLoader.loadClass("[C"), str.getBytes());
            jStr = classLoader.loadClass("java/lang/String").newObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jStr.setRefVar("value", "[C", jChars);
        map.put(str, jStr);
        return jStr;
    }

    public static String string(Objectz jStr) {
        Objectz charArr = jStr.getRefVar("value", "[C");
        return new String(charArr.bytes());
    }
}
