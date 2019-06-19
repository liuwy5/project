package com.io.objectStream;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person(12, "a", "a1", 3.2);
        Person p2 = new Person(13, "b", "b1", 4.2);
        Person p3 = new Person(14, "c", "c1", 5.2);
        Person p4 = new Person(15, "d", "d1", 6.2);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("file/objectStream1.txt"));

        outputStream.writeObject(p1);
        outputStream.writeObject(p2);
        outputStream.writeObject(p3);
        outputStream.writeObject(p4);

        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("file/objectStream1.txt"));
        Person person;
        for (int i = 0; i < 4; i++) {
            person = (Person) inputStream.readObject();
            System.out.println(person);
        }

        inputStream.close();
    }
}

class Person implements Serializable {
    int age;

    String name;

    transient String nick;

    double height;

    public Person(int age, String name, String nick, double height) {
        this.age = age;
        this.name = name;
        this.nick = nick;
        this.height = height;
    }

    @Override
    public String toString() {
        return "TheThread1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", height=" + height +
                '}';
    }
}
