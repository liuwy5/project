package com.concurrency.basic;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueueTest test = new BlockingQueueTest();
//        test.nonBlockingTest();
        test.blockingTest();
    }

    /**
     * 非阻塞队列实现生产者消费者
     */
    public void nonBlockingTest() {
        final Integer queueSize = 10;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        new NonProducer(queue, queueSize).start();
        new NonConsumer(queue).start();
    }

    /**
     * 阻塞队列实现生产者消费者
     */
    public void blockingTest() {
        final Integer queueSize = 10;
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);
        new BlockingProducer(queue).start();
        new BlockingConsumer(queue).start();
    }

    /**
     * 阻塞队列
     */
    public void queueTest() {

    }
}

class NonProducer extends Thread {

    private PriorityQueue<Integer> queue;

    private Integer queueSize;

    public NonProducer(PriorityQueue<Integer> queue, Integer queueSize) {
        this.queue = queue;
        this.queueSize = queueSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == queueSize) {
                    System.out.println("producer wait");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        queue.notify();
                    }
                }

                try {
                    Thread.sleep(2000);
                    queue.add(1);
                    System.out.println("producer 1, size: " + queue.size());

                    queue.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class NonConsumer extends Thread {
    private PriorityQueue<Integer> queue;

    public NonConsumer(PriorityQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == 0) {
                    System.out.println("consumer wait");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        queue.notify();
                    }
                }

                try {
                    Thread.sleep(1000);
                    Integer value = queue.poll();
                    System.out.println("consumer " + value + ", size: " + queue.size());

                    queue.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class BlockingProducer extends Thread {
    private ArrayBlockingQueue<Integer> queue;

    public BlockingProducer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                queue.put(1);
                System.out.println("producer size: " + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BlockingConsumer extends Thread {
    private ArrayBlockingQueue<Integer> queue;

    public BlockingConsumer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                queue.take();
                System.out.println("consumer size: " + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
