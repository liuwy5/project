package com.collections.collections;

import java.util.*;

public class CollectionsTest1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("z");
        list.add("d");

        System.out.println("-------------------Collections.reverseOrder()----------------");
        Comparator<Object> reverseOrder = Collections.reverseOrder();

        list.sort(reverseOrder);

        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);

        Object min = Collections.min(list);
        System.out.println(min);

        Object max = Collections.max(list, reverseOrder);
        System.out.println(max);

        System.out.println("-------------------Collections.reverseOrder()----------------");
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("b");
        System.out.println("list: " + list + ", list1: " + list1 + ", list.containsAll(list1): " + list.containsAll(list1));

    }
}
