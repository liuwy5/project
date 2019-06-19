package com.concurrency.basic.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * executor
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }

        executorService.shutdown();
    }
}
