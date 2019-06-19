package com.concurrency.basic.exception;

public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        int i = 3/0;
        System.out.println("thread body");
    }
}
