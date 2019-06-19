package com.collections.list;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add(1, "aa"); // 会越界
        Collections.addAll(list, "a", "b", "c");
        System.out.println(list);

        String value = "b";
        System.out.println("contains string: " + list.contains(value));

        list.add("b");
        list.removeAll(Arrays.asList("b"));
        System.out.println("remove: " + list);

        list.clear();
        Collections.addAll(list, "a", "b", "c");
        list.add("b");
        System.out.println("list: " + list);
        list.remove("b");
        System.out.println("remove: " + list);

        Object[] object = list.toArray();
        System.out.println("object[1]: " + object[1]);
        String[] strings = list.toArray(new String[2]);
        System.out.println("strings[1]: " + strings[1]);

        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        System.out.println(list1);

        List<Object> list2 = new ArrayList<>();
        list2.add(3);
        System.out.println(list2);
    }
}
