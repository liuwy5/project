package com.string;

import java.util.Random;

public class StringBuilderTest {

    public static String construct() {
        Random random = new Random(47);
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < 10; i++) {
            /**
             * 如果用append(a + ":" + c)，编译器会在循环内构造StringBuilder对象来处理括号内的字符串操作
             */
            builder.append(random.nextInt(100)).append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringBuilderTest.construct());
    }
}
