package com.collections.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        Collections.addAll(list, "a", "b", "c");

        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println("previous index: " + iterator.previousIndex() + ", next index: " + iterator.nextIndex()
            + ", item: " + iterator.next());
        }

        while (iterator.hasPrevious()) {
            System.out.println("previous index: " + iterator.previousIndex() + ", next index: " + iterator.nextIndex()
                    + ", item: " + iterator.previous());
        }

        iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(list);
    }
}
