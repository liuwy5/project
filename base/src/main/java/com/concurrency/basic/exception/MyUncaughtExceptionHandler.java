package com.concurrency.basic.exception;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("thread " + t.getName() + " exception: " + e);
    }
}
