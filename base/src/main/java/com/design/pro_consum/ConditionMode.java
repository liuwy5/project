package com.design.pro_consum;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionMode {
    private final int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>();
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionMode mode = new ConditionMode();
        mode.new Producer().start();
        mode.new Consumer().start();
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * Condition需要先获得lock锁
                 */
                lock.lock();
                try {
                    // 用while
                    while (queue.size() == queueSize) {
                        System.out.println("queue full, wait");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("produce size: " + queue.size());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("queue empty, await");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.poll();
                    notFull.signal();
                    System.out.println("consumer size: " + queue.size());
                } finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
