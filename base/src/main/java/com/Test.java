package com;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.*;

public class Test {
    public void s() {
        ExecutorService s = Executors.newFixedThreadPool(3);
        s.execute(new Thread());
    }

    public static void main(String[] args) {
        Collections.synchronizedList(null);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

//        list.pop();
        System.out.println(list);
        while (list.size() > 0) {
            System.out.println(list.pop());
        }
    }
}
