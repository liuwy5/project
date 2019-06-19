package com.statics;

public class StaticCodeBlockTest {
    public static void main(String[] args) {
        System.out.println("程序入口");
        new C();
        new C();
    }
}

class A {
    static {
        System.out.println("A static block");
    }

    public A () {
        System.out.println("A construct");
    }

    {
        System.out.println("A normal block");
    }
}

class B extends A {
    static {
        System.out.println("B static block");
    }

    public B () {
        System.out.println("B construct");
    }

    {
        System.out.println("B normal block");
    }
}

class C extends B {
    static {
        System.out.println("C static block");
    }

    public C() {
        System.out.println("C construct");
    }

    {
        System.out.println("C normal block");
    }
}
