package com.generic.high;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用时限制泛型类型
 */
public class GenericTest2<T> {
    private T foo;

    public T getFoo() {
        return foo;
    }

    public void setFoo(T foo) {
        this.foo = foo;
    }

    /**
     * 可以不同泛型的引用之间赋值 但是调用set方法时，因为T类型为? extends SomeClass，不确定为什么类型，所以不能编译通过
     * @param args
     */
    public static void main(String[] args) {
        GenericTest2<? extends List> genericTest1 = new GenericTest2<>();
//        genericTest1.setFoo(new ArrayList()); 不能确定T为ArrayList类型，所以不能set

        genericTest1 = new GenericTest2<ArrayList>();
//        genericTest1.setFoo(new ArrayList()); // 不能确定T为ArrayList类型，所以不能set
        genericTest1 = new GenericTest2<LinkedList>();
        System.out.println("--------------------------------");

        GenericTest2<? super List> genericTest2 = null;
        genericTest2 = new GenericTest2<Object>();

        GenericTest2<String> genericTest3 = new GenericTest2<>();
        genericTest3.setFoo("string");
        System.out.println(genericTest3.getFoo());

        GenericTest2<? extends Object> genericTest4 = genericTest3;
        System.out.println(genericTest4.getFoo());

        genericTest4.setFoo(null);
        System.out.println(genericTest3.getFoo());

//        genericTest4.setFoo("sss");
    }
}
