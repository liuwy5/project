package com.access.child;

import com.access.Parent;

public class Child extends Parent {
    private int s = 3;

    public Child() {}

    public Child(int s) {
        this.s = s;
    }

    private String name() {
        System.out.println("child");
        return "child";
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        // System.out.println(parent.i); 编译报错 只是因为继承，所以可以类本身访问

        Child child = new Child();
        System.out.println(child.i);
        System.out.println("Parent static: " + Parent.a);
        System.out.println("Child static: " + Child.a);
        a = 10;
        System.out.println("Parent static: " + Parent.a);
        System.out.println("Child static: " + Child.a);
    }
}
