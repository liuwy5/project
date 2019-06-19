package com.java.test.reflect;

import java.lang.annotation.Annotation;

public class Test1 {
    public static void main(String[] args) {
        Person person = new Person();
        Annotation[] annotations = person.getClass().getAnnotations();
        System.out.println(annotations.length);
        for (Annotation annotation : annotations) {
            System.out.println(annotation.getClass());
        }
    }
}

class Person {
    private String name;
}
