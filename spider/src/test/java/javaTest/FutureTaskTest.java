package javaTest;

import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) {
        FutureTask<String> futureTask1=new FutureTask(new CallableTest(1000));
        FutureTask<String> futureTask2=new FutureTask(new CallableTest(3000));

        
    }
}
