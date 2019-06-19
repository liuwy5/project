package com.io.objectStream;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) throws Exception {
        Person2 p1 = new Person2(12, "a", "a1", 3.2);
        Person2 p2 = new Person2(13, "b", "b1", 4.2);
        Person2 p3 = new Person2(14, "c", "c1", 5.2);
        Person2 p4 = new Person2(15, "d", "d1", 6.2);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("file/objectStream2.txt"));

        outputStream.writeObject(p1);
        outputStream.writeObject(p2);
        outputStream.writeObject(p3);
        outputStream.writeObject(p4);

        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("file/objectStream2.txt"));
        Person2 person;
        for (int i = 0; i < 4; i++) {
            person = (Person2) inputStream.readObject();
            System.out.println(person);
        }

        inputStream.close();
    }
}

class Person2 implements Serializable {
    int age;

    String name;

    transient String nick;

    double height;

    public Person2(int age, String name, String nick, double height) {
        this.age = age;
        this.name = name;
        this.nick = nick;
        this.height = height;
    }

    /**
     * 在序列化和反序列化过程中需要特殊处理的可序列化类应实现以下方法
     */
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        System.out.println("writeObject person2");
        stream.writeInt(age);
        stream.writeUTF(name);
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        System.out.println("readObject person2");
        age = stream.readInt();
        name = stream.readUTF();
    }

    @Override
    public String toString() {
        return "Person2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", height=" + height +
                '}';
    }
}
