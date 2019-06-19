package com.java.serializable;

import java.io.*;

public class SerializableTest2 {
    public static void main(String[] args) {
        Person2 person = new Person2(2, "aaa", 3.4f);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("serializable2");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);

            FileInputStream fileInputStream = new FileInputStream("serializable2");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Person2 person2 = (Person2) objectInputStream.readObject();
            System.out.println(person2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person2 implements Serializable {
    private int age;

    private String name;

    private float height;

    public Person2(int age, String name, float height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeInt(age);
        out.writeUTF(name);
        System.out.println("writeObject");
    }
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        age = in.readInt();
        name = in.readUTF();
        System.out.println("readObject");
    }

    @Override
    public String toString() {
        return "Person2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
