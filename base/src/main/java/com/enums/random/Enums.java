package com.enums.random;

import java.util.Random;

public class Enums {

    static Random random = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> tClass) {
        T[] values = tClass.getEnumConstants();
        return random(values);
    }

    public static <T> T random(T[] values) {
        return values[random.nextInt(values.length)];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(random(Season.class));
        }
    }
}

enum Season {
    SPRING, SUMMER, AUTUMN, WINTER;
}











