package com.collections.set;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetTest2 {
    public static void main(String[] args) {
        Set set = new HashSet();

        set.add(new Student("a"));
        set.add(new Student("a"));
        set.add(new Students("a"));

        System.out.println(set);
    }
}

class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Students extends Student {
    public Students(String name) {
        super(name);
    }
}
