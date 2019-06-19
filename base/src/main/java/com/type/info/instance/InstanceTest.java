package com.type.info.instance;

public class InstanceTest {
    public static void main(String[] args) {
        Person person = new Person();

        System.out.println("sub object instance of parent class " + (person instanceof Individual));

        System.out.println("sub object isInstance parent class " + Individual.class.isInstance(person));
    }
}

class Individual {

}

class Person extends Individual {
}