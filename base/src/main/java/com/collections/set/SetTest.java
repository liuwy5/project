package com.collections.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, "a", "b", "c", "e", "d");
        System.out.println(set);

        // iterator循环更改结构
        System.out.println("iterator循环更改结构: ");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("b".equals(value)) {
                iterator.remove();
            }
        }
        System.out.println(set);

        // set remove
        System.out.println("set remove: ");
        set.remove("c");
        System.out.println(set);

        // foreach循环更改结构 出现java.util.ConcurrentModificationException
        System.out.println("foreach循环更改结构: ");
        try {
            for (String value : set) {
                if ("a".equals(value)) {
                    set.remove(value);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(set);
    }
}
