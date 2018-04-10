package javaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) {
        List<FutureTask<String>> futureTaskList = new ArrayList<>();
        FutureTask<String> futureTask = null;
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            futureTask = new FutureTask<>(new CallableTest(1000*i));
            executorService.execute(futureTask);
            futureTaskList.add(futureTask);
        }

        try {
            for (FutureTask<String> f : futureTaskList
                    ) {
                System.out.println(f.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
