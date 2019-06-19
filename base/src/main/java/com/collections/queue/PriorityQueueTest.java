package com.collections.queue;

import java.util.*;

public class PriorityQueueTest {
    public static void main(String[] args) {
        List list = Arrays.asList(3, 5, 2, 4);

        Queue q = new LinkedList(list);
        QueueDemo.print(q);
        System.out.println();

        PriorityQueue queue = new PriorityQueue(list);
        QueueDemo.print(queue);
        System.out.println();

        queue = new PriorityQueue(list.size(), Collections.reverseOrder());
        queue.addAll(list);
        QueueDemo.print(queue);
    }
}
