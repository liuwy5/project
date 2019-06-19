package com.concurrency.basic.join;

/**
 * main 两个线程join
 */
public class JoinTest {

    public static void main(String[] args) {
        JoinTest joinTest = new JoinTest();
        joinTest.joinMulti();
    }

    public void joinMulti() {
        Thread thread1 = new JoinUnit("thread1");
        Thread thread2 = new JoinUnit("thread1");
        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + " run");
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }
}

class JoinUnit extends Thread {
    private String name;

    public JoinUnit(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
