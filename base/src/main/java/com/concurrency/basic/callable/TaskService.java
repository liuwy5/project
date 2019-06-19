package com.concurrency.basic.callable;

import java.util.concurrent.*;

public class TaskService {
    public static void main(String[] args) {
        TaskService service = new TaskService();
//        service.futureMethod();

        service.futureTaskMethod();
    }

    /**
     * Future接口
     */
    public void futureMethod() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();

        System.out.println("main thread run");
        try {
            /**
             * Future.get()方法会阻塞，会一直等到任务执行完毕返回
             */
            Integer result = future.get();
            System.out.println("task result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * FutureTask
     */
    public void futureTaskMethod() {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        /**
         * 1 使用ExecutorService
         */
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.submit(futureTask);
//        executorService.shutdown();

        /**
         * 2 使用Thread
         */
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("main thread run ");
        try {
            /**
             * futureTask.get()方法会阻塞，会一直等到任务执行完毕返回
             */
            Integer result = futureTask.get();
            System.out.println("task result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("task execute");
        Thread.sleep(2000);
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += i;
        }
        return result;
    }
}