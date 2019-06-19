package com.collections.set;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest3 {
    public static void main(String[] args) {
        Stu stu1 = new Stu("a", 11);
        Stu stu2 = new Stu("b", 18);
        Stu stu3 = new Stu("c", 13);
        Set<Stu> set = new TreeSet<>();
        set.add(stu1);
        set.add(stu2);
        set.add(stu3);
        System.out.println(set);
    }
}

class Stu implements Comparable<Stu> {

    private String name;

    private int age;

    public Stu(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Stu o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
