package com.concurrency.basic.executor;

public class LiftOff implements Runnable {
    protected int countDown = 10;

    private static int taskCount = 0;

    private final int id = taskCount++;

    @Override
    public String toString() {
        return "#" + id + "(" + countDown + ")";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(this);
            Thread.yield();
        }
    }
}
