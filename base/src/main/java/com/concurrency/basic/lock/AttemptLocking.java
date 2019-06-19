package com.concurrency.basic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    // tryLock
    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock: " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            // 如果在给定的等待时间内没有被另一个线程占用 ，并且当前线程尚未被保留，则获取该锁
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking locking = new AttemptLocking();
        locking.untimed();
        locking.timed();

        new Thread(){
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                locking.lock.lock();
                System.out.println("acquired");
            }
        }.start();

        Thread.yield();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        locking.untimed();
        Thread.yield();
        locking.timed();
    }
}
