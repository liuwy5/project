package com.concurrency.basic.exception;

import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        System.out.println("current thread is: " + thread.getName());
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return thread;
    }
}
