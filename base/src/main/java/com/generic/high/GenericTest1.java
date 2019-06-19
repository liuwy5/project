package com.generic.high;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 通过extends现在泛型类型
 */
public class GenericTest1<T extends List> {
    private T foo;

    public T getFoo() {
        return foo;
    }

    public void setFoo(T foo) {
        this.foo = foo;
    }

    public static void main(String[] args) {
        GenericTest1<ArrayList> genericTest1 = new GenericTest1<>();
        GenericTest1<LinkedList> genericTest2 = new GenericTest1<>();
//        genericTest1 = genericTest2;  genericTest1和genericTest2是不同类型
    }
}
