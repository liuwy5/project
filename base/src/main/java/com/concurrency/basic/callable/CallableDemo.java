package com.concurrency.basic.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 获取结果
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> result = executorService.submit(new TaskWithResult());
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            futures.add(executorService.submit(new TaskWithResult()));
        }

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }

        }
    }
}

class TaskWithResult implements Callable<String> {
    private static int count = 0;

    private final int id = count++;


    @Override
    public String call() throws Exception {
        if (id == 2) {
            Thread.sleep(3000);
        }
        return "id(" + id + ")";
    }
}
