package com.concurrency.basic;

public class InterruptTest {
    public static void main(String[] args) {
        InterruptTest interruptTest = new InterruptTest();
//        interruptTest.interruptBlockThread();
//        interruptTest.interruptRunThread();
        interruptTest.interruptRunThreadRecommend();
    }

    public void interruptBlockThread() {
        InterruptUnit interruptUnit = new InterruptUnit();
        System.out.println(Thread.currentThread().getName() + " run");
        interruptUnit.start();
        interruptUnit.interrupt();
    }

    public void interruptRunThread() {
        InterruptRunThread thread = new InterruptRunThread();
        thread.start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public void interruptRunThreadRecommend() {
        InterruptRunThreadRecommend thread = new InterruptRunThreadRecommend();
        thread.start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setStop(true);
    }
}

/**
 * 中断阻塞线程 将给该线程设定一个标志 表示线程已中断
 * catch异常之后，会清理这个标志 所以isInterrupted()返回false
 */
class InterruptUnit extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted, isInterrupted: " + isInterrupted() +
                    " , isAlive: " + isAlive());
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }
}

/**
 * 直接调用interrupt方法不能中断一个正在运行的线程
 * 可以通过interrupt方法和isInterrupted()方法来停止正在运行的线程
 * 因为调用interrupt方法相当于将中断标志位置为true，那么可以通过调用isInterrupted()判断中断标志是否被置位来中断线程的执行
 * 不过一般不建议用这种方式中断线程 一般用一个状态变量
 */
class InterruptRunThread extends Thread {
    @Override
    public void run() {
        int i = 0;
        while (!isInterrupted() && i < Integer.MAX_VALUE) {
            System.out.println(i++);
        }
    }
}

/**
 * 一般会在MyThread类中增加一个属性 isStop来标志是否结束while循环，然后再在while循环中判断isStop的值
 * 那么就可以在外面通过调用setStop方法来终止while循环
 */
class InterruptRunThreadRecommend extends Thread {

    private volatile boolean isStop = false;

    public void setStop(boolean stop) {
        this.isStop = stop;
    }

    @Override
    public void run() {
        int i = 0;
        while (!isStop && i < Integer.MAX_VALUE) {
            System.out.println(i++);
        }
    }
}