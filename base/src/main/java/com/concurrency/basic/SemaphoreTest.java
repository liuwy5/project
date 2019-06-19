package com.concurrency.basic;

import java.util.concurrent.Semaphore;

/**
 * 控制同时访问同一组资源的线程个数
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        SemaphoreTest test = new SemaphoreTest();
        test.method();
    }

    public void method() {
//        Semaphore semaphore = new Semaphore(5);

        Semaphore semaphore = new Semaphore(5, true); // 公平
        for (int i = 0; i < 8; i++) {
            new Worker(i, semaphore).start();
        }
    }
}

class Worker extends Thread {
    private int number;

    private Semaphore semaphore;

    public Worker(int number, Semaphore semaphore) {
        this.number = number;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 获得锁
//            semaphore.acquire();
//            System.out.println("worker " + number + " work");
//
//            Thread.sleep(3000);
//            System.out.println("worker " + number + " end");
//
//            semaphore.release();


            // 尝试获得锁
            if (semaphore.tryAcquire()) {
                System.out.println("worker " + number + " work");
                Thread.sleep(3000);
                System.out.println("worker " + number + " end");

                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
