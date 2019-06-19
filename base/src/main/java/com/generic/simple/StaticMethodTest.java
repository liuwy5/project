package com.generic.simple;

public class StaticMethodTest {
    public <T> void method(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        StaticMethodTest staticMethodTest = new StaticMethodTest();

        staticMethodTest.method(3);
    }
}
