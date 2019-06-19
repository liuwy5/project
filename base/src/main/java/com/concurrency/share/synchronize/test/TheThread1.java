package com.concurrency.share.synchronize.test;

/**
 * synchronized会对对象(Person)加锁
 */
public class TheThread1 {

    public static void main(String[] args) {
        Person person = new Person();

        Thread thread1 = new Thread(new MyThread1(person));
        Thread thread2 = new Thread(new MyThread2(person));

        thread1.start();
        thread2.start();
    }
}

class Person {
    public synchronized void method() {
        for (int i = 0; i < 10; i++) {
            System.out.println("method " + i);
        }
    }

    public synchronized void method1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("method1 " + i);
        }
    }
}

class MyThread1 implements Runnable {

    private Person person;

    public MyThread1(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.method();
    }
}

class MyThread2 implements Runnable {

    private Person person;

    public MyThread2(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.method1();
    }
}

