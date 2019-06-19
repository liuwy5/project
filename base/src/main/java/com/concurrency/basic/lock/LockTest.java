package com.concurrency.basic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
//        lockTest.lockInterrupt();
//        lockTest.lockTry();
        lockTest.lockTryTime();
    }

    /**
     * 中断锁
     * 当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。
     * 也就是说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，
     * 那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程
     */
    private void lockInterrupt() {
        class InterruptThread extends Thread {
            private InterruptThread(String name) {
                super(name);
            }

            @Override
            public void run() {
                try {
                    lockInterruptMethod();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "获取锁中断");
                }
            }
        }
        Thread thread1 = new InterruptThread("name1");
        Thread thread2 = new InterruptThread("name2");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断
        thread1.interrupt();
        thread2.interrupt();
    }

    private void lockInterruptMethod() throws InterruptedException {
        // lockInterruptibly 如果需要正确中断等待锁的线程，必须将获取锁放在外面 然后将InterruptedException抛出
        lock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
//            Thread.sleep(3000);
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 尝试获取锁
     */
    private void lockTry() {
        class LockTryThread extends Thread {
            public LockTryThread(String name) {
                super(name);
            }

            @Override
            public void run() {
                if (lock.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取锁");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "尝试获取锁失败");
                }
            }
        }
        new LockTryThread("name1").start();
        new LockTryThread("name2").start();
    }

    /**
     * 尝试获取锁 等待一定时间
     */
    private void lockTryTime() {
        class LockTryTimeThread extends Thread {
            @Override
            public void run() {
                try {
                    if (lock.tryLock(1L, TimeUnit.SECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取锁");
                            Thread.sleep(2000);
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "尝试获取锁失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        new LockTryTimeThread().start();
        new LockTryTimeThread().start();
        new LockTryTimeThread().start();
    }
}
