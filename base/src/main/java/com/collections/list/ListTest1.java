package com.collections.list;

import java.util.*;

public class ListTest1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "e", "d");
        System.out.println(list);

        // iterator循环更改结构
        System.out.println("iterator循环更改结构: ");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("b".equals(value)) {
                iterator.remove();
            }
        }
        System.out.println(list);

        // set remove
        System.out.println("set remove: ");
        list.remove("c");
        System.out.println(list);

        // foreach循环更改结构 出现java.util.ConcurrentModificationException
        System.out.println("foreach循环更改结构: ");
        try {
            for (String value : list) {
                if ("a".equals(value)) {
                    try {
                        list.remove(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
    //                    e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(list);

        // for get 循环更改结构
         System.out.println("for get 循环更改结构: ");
        for (int i = 0; i < list.size(); i++) {
            if ("e".equals(list.get(i))) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}
