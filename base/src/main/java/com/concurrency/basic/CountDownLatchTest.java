package com.concurrency.basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new CountDownLatchUnit(countDownLatch).start();
        new CountDownLatchUnit(countDownLatch).start();

        try {
//            countDownLatch.await();  //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
            countDownLatch.await(2, TimeUnit.SECONDS);  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
            System.out.println("main execute");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CountDownLatchUnit extends Thread {

    private CountDownLatch countDownLatch;

    public CountDownLatchUnit(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
        countDownLatch.countDown(); // 将count值减1
    }
}

