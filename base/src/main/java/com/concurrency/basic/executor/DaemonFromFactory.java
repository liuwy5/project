package com.concurrency.basic.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 每个静态的ExecutorService构建方法都被重载为接受一个ThreadFactory对象，而这个对象将被用来创建新的线程
 */
public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        System.out.println("run");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());

        executorService.execute(new DaemonFromFactory());

        executorService.shutdown();
    }
}


