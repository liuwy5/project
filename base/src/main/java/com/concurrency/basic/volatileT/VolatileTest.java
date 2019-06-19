package com.concurrency.basic.volatileT;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    public static void main(String[] args) {
        VolatileAtomic volatileAtomic = new VolatileAtomic();
        volatileAtomic.method();
    }
}

/**
 * volatile无法保证操作的原子性
 * 自增操作不具备原子性
 * 线程1对变量进行读取操作之后，被阻塞了的话，并没有对inc值进行修改。
 * 然后虽然volatile能保证线程2对变量inc的值读取是从内存中读取的，但是线程1没有进行修改，所以线程2根本就不会看到修改的值。
 */
class VolatileAtomic {
    private volatile int index = 0;

    public void increase() {
        index++;
    }

    public void method() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            }.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }
}

/**
 * increase方法用synchronized或Lock加锁实现原子性
 */
class AtomicAdvance {
    private int index = 0;

    /**
     * 1 synchronized关键字
     */
    public synchronized void increase() {
        index++;
    }

    /**
     * 2 Lock
     */
    Lock lock = new ReentrantLock();
    public void increase1() {
        lock.lock();
        try {
            index++;
        } finally {
            lock.unlock();
        }
    }

    public void method() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            }.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }
}

/**
 * 采用AtomicInteger
 * 对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），减法操作（减一个数）进行了封装，保证这些操作是原子性操作。
 * atomic是利用CAS来实现原子性操作的
 */
class AtomicAdvance2 {
    private AtomicInteger index = new AtomicInteger();

    /**
     * 3 AtomicInteger
     */
    public void increase() {
        index.getAndIncrement();
    }

    public void method() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            }.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }
}
