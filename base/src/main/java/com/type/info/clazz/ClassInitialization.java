package com.type.info.clazz;

import java.util.Random;

public class ClassInitialization {
    public static Random random = new Random(47);

    public static void main(String[] args) {
        Class clazz = InitialClass.class;
        System.out.println("after creating InitialClass ref");

        System.out.println(InitialClass.value1);
        System.out.println(InitialClass.value2);

        System.out.println(InitialClass1.value);

        try {
            clazz = Class.forName("com.type.info.clazz.InitialClass2");
            System.out.println("after creating InitialClass2 ref");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(InitialClass2.value1);
    }
}

class InitialClass {
    static final int value1 = 3;

    static final int value2 = ClassInitialization.random.nextInt(1000);

    static {
        System.out.println("InitialClass static block");
    }
}

class InitialClass1 {
    static int value = 1;

    static {
        System.out.println("InitialClass1 static block");
    }
}

class InitialClass2 {
    static final int value1 = 3;

    static {
        System.out.println("InitialClass2 static block");
    }
}
