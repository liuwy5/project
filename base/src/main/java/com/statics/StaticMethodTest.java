package com.statics;

public class StaticMethodTest {
    public static void main(String[] args) {
        E e = new E();
        e.method1();
        e.method();
        e.method2();

        D d = new E();
        d.method1();
        d.method(); // 会调用父类的static方法
    }
}

class D {
    public static void method() {
        System.out.println("D method");
    }

    public void method1() {
        System.out.println("D method1");
    }

    public final void method2() {
        System.out.println("D method2");
    }
}

class E extends D {
    public static void method() {
        System.out.println("E method");
    }

    public void method1() {
        System.out.println("E method1");
    }
}