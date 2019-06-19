package com.concurrency.basic.join;

import java.util.Date;

/**
 * join
 * interrupt
 */
public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("sleeper", 2000),
                sleeper1 = new Sleeper("sleeper1", 2000);

        Joiner joiner = new Joiner("joiner", sleeper),
                joiner1 = new Joiner("joiner1", sleeper1);

        System.out.println("main continue");

        sleeper1.interrupt();
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();

            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " joiner completed " + new Date());
    }
}

class Sleeper extends Thread {
    private int time;

    public Sleeper(String name, int time) {
        super(name);
        this.time = time;
        start();
    }

    public void run() {
        try {
            sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 被中断后catch子句中，将根据isInterrupted()的返回值报告这个中断。
             * 当另一个线程在该线程上调用interrupt()时，将给该线程设定一个标志，表明该线程已经被中断
             * 然而，异常被捕获时将清理这么标志，所以在catch子句中，在异常被捕获的时候这个标志总是为假
             */
            System.out.println(this.getName() + " sleeper interrupted. isInterrupted: " + this.isInterrupted() + " " + new Date());
        }

        System.out.println(this.getName() + " asleep " + new Date());
    }
}
