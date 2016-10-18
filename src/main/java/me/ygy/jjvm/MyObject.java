package me.ygy.jjvm;

import me.ygy.jjvm.rtda.heap.Objectz;

/**
 * Created by guangyuanyu on 2016/10/18.
 */
public class MyObject {

    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768;
        MyObject myObject = new MyObject();
        MyObject.staticVar = x;
        x = MyObject.staticVar;
        myObject.instanceVar = x;
        x = myObject.instanceVar;
        Object obj = myObject;
        if (obj instanceof MyObject) {
            myObject = (MyObject) obj;
            System.out.println(myObject.instanceVar);
        }
    }
}
