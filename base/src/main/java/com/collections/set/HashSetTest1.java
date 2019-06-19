package com.collections.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest1 {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("a");
        set.add("b");
        set.add("c");
        System.out.println(set);

        set.clear();
        set.add(new String("a"));
        set.add(new String("a"));
        System.out.println(new String("a").hashCode() == new String("a").hashCode());
        System.out.println(set); // 两个字符串对象的hashCode相同，因为String重写了hashCode方法

        set.clear();
        set.add(new People("a"));
        set.add(new People("a"));
        System.out.println(set);

        set.clear();
        People people = new People("a");
        set.add(people);
        set.add(people);
        System.out.println(set);
    }
}

class People {
    String name;

    public People(String name) {
        this.name = name;
    }
}
