package com.concurrency.basic;

public class YieldTest {

    public static void main(String[] args) {
        YieldTest yieldTest = new YieldTest();
        yieldTest.yieldPriorityMethod();
    }

    public void yieldPriorityMethod() {
        new YieldUnit(3).start();
        new YieldUnit(2).start();
    }
}

class YieldUnit extends Thread {
    private int priority;

    public YieldUnit(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        setPriority(priority);
        System.out.println(Thread.currentThread().getName() + " execute");
        yield();
        System.out.println(Thread.currentThread().getName() + " continue");
    }
}