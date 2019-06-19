package com.concurrency.share.generator;

public class SynchronizedEvenGenerator extends IntGenerator {
    int value = 0;

    @Override
    public synchronized int next() {
        ++value;
        Thread.yield();
        ++value;
        return value;
    }

    public static void main(String[] args) {
        Task.test(new SynchronizedEvenGenerator(), 10);
    }
}
