package com.concurrency.share.synchronize.test;

/**
 * synchronized代码块更加细粒度控制同步方法内容
 */
public class TheThread3 {
    public static void main(String[] args) {
        Person3 person = new Person3();

        Thread thread1 = new Thread(new MyThread5(person));
        Thread thread2 = new Thread(new MyThread6(person));

        thread1.start();
        thread2.start();
    }
}

class Person3 {
    public void method() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("method " + i);
            }
        }
    }

    public void method1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("method1 " + i);
            }
        }
    }
}

class MyThread5 implements Runnable {

    private Person3 person;

    public MyThread5(Person3 person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.method();
    }
}

class MyThread6 implements Runnable {

    private Person3 person;

    public MyThread6(Person3 person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.method1();
    }
}