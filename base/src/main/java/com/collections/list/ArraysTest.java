package com.collections.list;

import java.util.*;

public class ArraysTest {
    public static void main(String[] args) {
        List<Animal> list = Arrays.asList(new Cat(), new Dog());

        List<Animal> list1 = Arrays.asList(new DogA(), new DogB());

        System.out.println(list);
        System.out.println(list1);

        System.out.println();

        Animal[] animals = {new Cat(), new Dog(), new DogA(), new DogB()};
        System.out.println("array: " + Arrays.toString(animals));
        List<Animal> list2 = Arrays.asList(animals);
        System.out.println(list2);
        /**
         * Arrays.asList()产生的List对象会使用底层数组作为其物理实现
         * 只要执行的操作会修改这个list，而不想原来的数组被修改，那么应该在另一个容器里创建一个副本
         */
        Collections.shuffle(list2, new Random(47));
        System.out.println(list2);
        System.out.println("array: " + Arrays.toString(animals));
        System.out.println();

        Animal[] animals1 = {new Cat(), new Dog(), new DogA(), new DogB()};
        System.out.println("array: " + Arrays.toString(animals1));
        List<Animal> list3 = Arrays.asList(animals1);
        System.out.println(list3);
        /**
         * 在另一个容器里创建一个副本
         */
        List list4 = new ArrayList(list3);
        Collections.shuffle(list4, new Random(47));
        System.out.println(list4);
        System.out.println("array: " + Arrays.toString(animals1));
    }
}

class Animal {
    @Override
    public String toString() {
        return "Animal";
    }
}

class Cat extends Animal {
    @Override
    public String toString() {
        return "Cat";
    }
}

class Dog extends Animal {
    @Override
    public String toString() {
        return "Dog";
    }
}

class DogA extends Dog {
    @Override
    public String toString() {
        return "DogA";
    }
}

class DogB extends Dog {
    @Override
    public String toString() {
        return "DogB";
    }
}
