package com.collections.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest2 {
    public static void main(String[] args) {
        TreeSet set = new TreeSet(new PersonComparator());

        Person person = new Person("a", 20);
        Person person1 = new Person("b", 40);
        Person person2 = new Person("c", 30);
        Person person3 = new Person("d", 50);

        set.add(person);
        set.add(person1);
        set.add(person2);
        set.add(person3);
        System.out.println(set);

        System.out.println(set.first());

        System.out.println();
    }
}

class Person {
    String name;

    int score;

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

class PersonComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Person person1 = (Person) o1;
        Person person2 = (Person) o2;
        return person1.score - person2.score;
    }
}
