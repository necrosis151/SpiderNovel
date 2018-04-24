package javaTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountdownlatch {


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 3; i++) {
            new myThread(latch).start();
        }
        new testThread(latch).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}

class testThread extends Thread {
    private CountDownLatch latch;

    public testThread(CountDownLatch latch) {
        this.latch = latch;
    }

    private void goTest() {
        System.out.println(Thread.currentThread().getName() + ":测试开始");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();

        System.out.println(Thread.currentThread().getName() + ":测试结束");

    }

    @Override
    public void run() {
        goTest();
    }
}

class myThread extends Thread {
    private CountDownLatch latch;

    public myThread(CountDownLatch latch) {
        this.latch = latch;
    }

    private void goTest() {
        System.out.println(Thread.currentThread().getName() + ":测试开始");

        latch.countDown();
        try {
            latch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":测试结束");

    }

    @Override
    public void run() {
        goTest();
    }
}
