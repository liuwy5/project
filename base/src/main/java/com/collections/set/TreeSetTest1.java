package com.collections.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest1 {
    public static void main(String[] args) {
        Set set = new TreeSet();
        set.add("c");
        set.add("A");
        set.add("z");
        set.add("C");
        System.out.println(set);

        set = new TreeSet(new StringReverse());
        set.add("c");
        set.add("A");
        set.add("z");
        set.add("C");
        System.out.println(set);
    }
}

class StringReverse implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String value1 = (String) o1;
        String value2 = (String) o2;
        return value2.compareTo(value1);
    }
}
