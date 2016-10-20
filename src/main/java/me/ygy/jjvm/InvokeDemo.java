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
        InvokeDemo.staticMethod(); //invoke static
        InvokeDemo demo = new InvokeDemo(); //invoke special
        demo.instanceMethod(); //invoke virtual
        super.equals(null); //invoke special
        this.run(); //invoke virtual
        ((Runnable)demo).run(); //invoke interface
    }

    public static void staticMethod() {
    }

    public void instanceMethod() {
    }
}
