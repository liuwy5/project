package com.type.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivateTest {
    public static void main(String[] args) throws Exception {
        System.out.println("--------------调用带参数的构造方法--------------");
        Class clazz = Student.class;
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Student student = (Student) constructor.newInstance("name", 14);
        System.out.println(student);

        System.out.println("--------------private field: getDeclaredField--------------");
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // 可访问性设置 压制java对访问修饰符的检查
        System.out.println("name is: " + nameField.get(student));

        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        System.out.println("age is: " + ageField.get(student));

        System.out.println("--------------private method: getDeclaredMethod--------------");
        Method method = clazz.getDeclaredMethod("checkIn");
        method.setAccessible(true);
        method.invoke(student);
    }
}

class Student {
    private String name;

    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void checkIn() {
        System.out.println(name + " checked in");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
