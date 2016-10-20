package me.ygy.jjvm;

/**
 * Created by guangyuanyu on 2016/10/20.
 */
public class InvokeDemo implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new InvokeDemo().test();
    }

    public void test() {
        InvokeDemo.staticMethod();
        InvokeDemo demo = new InvokeDemo();
        demo.instanceMethod();
        super.equals(null);
        this.run();
        demo.run();
    }

    public static void staticMethod() {

    }

    public void instanceMethod() {

    }
}
