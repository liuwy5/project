package com.base;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(11)); // 区间为左必右开
        }

        System.out.println("-----------------------------");

        // Math.random
        for (int i = 0; i < 10; i++) {
            double d = Math.random(); // [0.0, 1.0)
            double d1 = d * 11; // [0.0, 11.0)
            int d2 = (int) d1;
            System.out.println(d2);
        }
    }
}
