package com.concurrency.share.generator;

public class EvenGenerator extends IntGenerator {
    private int value = 0;

    @Override
    public int next() {
        ++value;
        Thread.yield();
        ++value;
        return value;
    }

    public static void main(String[] args) {
        Task.test(new EvenGenerator(), 10);
    }
}
