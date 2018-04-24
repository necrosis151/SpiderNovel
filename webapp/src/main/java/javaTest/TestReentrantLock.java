package javaTest;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new LockTest(lock).test1();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                new LockTest(lock).test2();
            }
        }).start();
    }
}

class LockTest {
    ReentrantLock lock;

    public LockTest(ReentrantLock lock) {
        this.lock = lock;
    }

    public void test1() {
        lock.lock();
        System.out.println("test1 休眠");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("test1 结束");
    }

    public void test2() {
        lock.lock();
        System.out.println("test2 进入");
        lock.unlock();
    }
}
