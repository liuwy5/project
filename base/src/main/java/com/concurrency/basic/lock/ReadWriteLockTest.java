package com.concurrency.basic.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReadWriteLockTest test = new ReadWriteLockTest();
//        test.synchronMethod();
        test.readLock();
    }

    /**
     * 读锁 可以同时读
     */
    private void readLock() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        class ReadLockThread extends Thread {
            public ReadLockThread(String name) {
                super(name);
            }

            @Override
            public void run() {
                lockExecute(lock);
            }
        }

        new ReadLockThread("name1").start();
        new ReadLockThread("name2").start();
    }

    /**
     * 假如有多个线程要同时进行读操作的话，synchronized会一个线程运行完后 另一个线程执行
     */
    private void synchronMethod() {
        class SynchronThread extends Thread {
            public SynchronThread(String name) {
                super(name);
            }

            @Override
            public void run() {
                synchronExecute();
            }
        }

        new SynchronThread("name1").start();
        new SynchronThread("name2").start();
    }

    private void lockExecute(ReentrantReadWriteLock lock) {
        lock.readLock().lock();
        try {
            execute();
        } finally {
            lock.readLock().unlock();
        }
    }

    private synchronized void synchronExecute() {
        execute();
    }

    private void execute() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " execute");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
