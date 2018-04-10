package javaTest;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<String> {

    private int millis = 0;

    public CallableTest(int millis) {
        this.millis = millis;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"test begin");
        Thread.sleep(millis);
        return Thread.currentThread().getName()+"over";
    }
}
