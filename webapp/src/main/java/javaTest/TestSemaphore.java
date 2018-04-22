package javaTest;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new MyThread(semaphore).start();
        }
    }

}

class MyThread extends Thread {
    Semaphore semaphore;

    public MyThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire()) {

                System.out.println(Thread.currentThread().getName() + "开始");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "结束");
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
