package com.concurrency.basic.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureUncaughtException {
    public static void main(String[] args) {
        // 如果代码中处处使用相同的异常处理器，则可设置默认的未捕获异常处理器
        // Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());

        executorService.execute(new ExceptionThread());

        executorService.shutdown();
    }
}
