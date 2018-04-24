package javaTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,new Runnable() {
            @Override
            public void run() {
                System.out.println("线程组执行结束");
            }
        });
        for (int i=0;i<2;i++){
            new MyThread2(cyclicBarrier).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<5;i++){
            new MyThread2(cyclicBarrier).start();
        }
        System.out.println("主线程结束");
    }
}

class MyThread2 extends  Thread{
    private CyclicBarrier cyclicBarrier;
    public MyThread2(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }
    public void goTest(){
        System.out.println(Thread.currentThread().getName() + ":测试开始");

        try {
            Thread.sleep(3000);
            try {
                cyclicBarrier.await(3, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":测试结束");

    }
    public void run(){
        goTest();
    }
}
