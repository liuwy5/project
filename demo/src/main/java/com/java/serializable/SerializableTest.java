package com.java.serializable;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) {
        Person p = new Person(4, "aa");
        try {
            FileOutputStream fos = new FileOutputStream("serializable");

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(p);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("serializable");

            ObjectInputStream ois = new ObjectInputStream(fileInputStream);

            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Person implements Serializable {
    private int age;

    transient private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
