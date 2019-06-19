package com.collections.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueDemo {
    public static void print(Queue queue) {
        while (queue.peek() != null) {
            System.out.println(queue.remove());
        }
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        Queue queue = new LinkedList();
        for (int i = 0; i < 10; i ++) {
            queue.offer(random.nextInt(30));
        }
        print(queue);
    }
}
