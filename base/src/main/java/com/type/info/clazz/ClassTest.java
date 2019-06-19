package com.type.info.clazz;

import java.lang.reflect.Constructor;

public class ClassTest {
    public static void main(String[] args) {
        Class clazz = ClassTest.class;

        for (Constructor constructor : clazz.getConstructors()) {
            System.out.println(constructor);
        }

        clazz = ConstructorClass.class;

        for (Constructor constructor : clazz.getConstructors()) {
            System.out.println(constructor);
        }

        for (Constructor constructor : clazz.getDeclaredConstructors()) {
            System.out.println(constructor);
        }

        clazz = ClassInitialization.class;

        for (Constructor constructor : clazz.getConstructors()) {
            System.out.println(constructor);
        }
    }
}

class ConstructorClass {

}
