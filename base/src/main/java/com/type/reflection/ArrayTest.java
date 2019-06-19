package com.type.reflection;

import java.lang.reflect.Array;

public class ArrayTest {
    public static void main(String[] args) {
        System.out.println("-------------------------------一维数组-------------------------------");
        Object object = Array.newInstance(String.class, 3);
        Array.set(object, 2, "a");
        String item = (String) Array.get(object, 2);
        System.out.println("一维数组item: " + item);

        System.out.println("-------------------------------多维数组-------------------------------");
        Object object1 = Array.newInstance(Integer.class, 2, 4, 3);// 第二个参数为可变参数，表示数组的各个维度的长度
        Object value = Array.get(object1, 1);
        value = Array.get(value, 2);
        Array.set(value, 1, 15);
        Integer[][][] object2 = (Integer[][][]) object1;
        System.out.println(object2[1][2][1]);
    }
}
