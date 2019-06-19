package com.concurrency.basic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 可以实现让一组线程等待至某个状态之后再全部同时执行
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrierTest test = new CyclicBarrierTest();
//        test.method1();

//        test.barrierActionMethod();

//        test.timeoutMethod();

        test.cycleMethod();

//        test.cycleMethodAgain();
    }

    /**
     * 挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务
     */
    public void method1() {
        int count = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }
    }

    /**
     * 当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable
     * Runnable执行完成后 四个线程再一起执行后续自己的任务
     */
    public void barrierActionMethod() {
        int count = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行barrierAction任务");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 0; i < count; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }
    }

    /**
     * 超过超时时间后
     */
    public void timeoutMethod() {
        int count = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            if (i == 2) {
                new CyclicBarrierUnitTimeout(cyclicBarrier, i + 3).start();
            } else {
                new CyclicBarrierUnitTimeout(cyclicBarrier, i).start();
            }
        }
    }

    /**
     * 重用
     */
    public void cycleMethod() {
        int count = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < count; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }
    }

    /**
     * 重用
     * 只要凑足count个 就可以继续执行 否则等待
     */
    public void cycleMethodAgain() {
        int count = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 0; i < count - 2; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < count; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }

        for (int i = 0; i < count - 2; i++) {
            new CyclicBarrierUnit(cyclicBarrier, i).start();
        }
    }
}

class CyclicBarrierUnit extends Thread {

    private CyclicBarrier cyclicBarrier;

    private int sleepTime;

    public CyclicBarrierUnit(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public CyclicBarrierUnit(CyclicBarrier cyclicBarrier, int sleepTime) {
        this.cyclicBarrier = cyclicBarrier;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        try {
            Thread.sleep(sleepTime * 1000);
            System.out.println(Thread.currentThread().getName() + " sleep " + sleepTime + " end");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}

class CyclicBarrierUnitTimeout extends Thread {

    private CyclicBarrier cyclicBarrier;

    private int sleepTime;

    public CyclicBarrierUnitTimeout(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public CyclicBarrierUnitTimeout(CyclicBarrier cyclicBarrier, int sleepTime) {
        this.cyclicBarrier = cyclicBarrier;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        try {
            Thread.sleep(sleepTime * 1000);
            System.out.println(Thread.currentThread().getName() + " sleep " + sleepTime + " end");

            // await(3, TimeUnit.SECONDS) 要被try catch包住
            try {
                cyclicBarrier.await(3, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
