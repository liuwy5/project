package com.collections.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SimpleIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println("s: " + s);
        }
        System.out.println();

        // iterator一行显示
        for (Iterator<String> iterator1 = list.iterator(); iterator1.hasNext(); ) {
            String s = iterator1.next();
            System.out.println("s: " + s);
        }

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();

        iterator = list.iterator();
        for (int i = 0; i < 2; i++) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(list);
    }
}
