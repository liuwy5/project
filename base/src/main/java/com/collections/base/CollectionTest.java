package com.collections.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        List list1 = Arrays.asList("a", "b", "c");
        System.out.println(list.retainAll(list1));
        System.out.println(list.retainAll(list1));

        Object[] objects = list.toArray();
        printArr(objects);

        String[] strings = list.toArray(new String[0]);
        printArr(strings);
    }

    public static void printArr(Object[] objects) {
        StringBuilder stringBuilder = new StringBuilder("Array: [");
        if (objects.length > 0) {
            for (Object object : objects) {
                stringBuilder.append(object).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder);
    }
}
