package com.concurrency.share.generator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task extends Thread {

    private IntGenerator intGenerator;

    public Task(IntGenerator intGenerator) {
        this.intGenerator = intGenerator;
    }

    @Override
    public void run() {
        while (!intGenerator.isLock()) {
            int value = intGenerator.next();
            if (value % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + intGenerator.next());
                intGenerator.lock();
            }
        }
    }

    public static void test(IntGenerator intGenerator, int count) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new Task(intGenerator));
        }
        executorService.shutdown();
    }

}
