package com.design.strategy.sortList;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Person person1 = new Person(1, 2, "a");
        Person person2 = new Person(2, 2, "b");
        Person person3 = new Person(5, 2, "c");
        Person person4 = new Person(3, 4, "a");
        Person person5 = new Person(4, 2, "d");
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        Environment environment = new Environment();

        environment.setSortList(new NameAscSort());
        environment.sort(personList);
        System.out.println(personList);

        environment.setSortList(new NameDescSort());
        environment.sort(personList);
        System.out.println(personList);
    }
}
