package com.concurrency.share.synchronize.test;

/**
 * synchronized修饰static方法时，会对类的class对象加锁
 * 所以即便是两个person对象 也是顺序执行
 */
public class TheThread2 {
    public static void main(String[] args) {
        Person2 person1 = new Person2();
        Person2 person2 = new Person2();

        Thread thread1 = new Thread(new MyThread3(person1));
        Thread thread2 = new Thread(new MyThread4(person2));

        thread1.start();
        thread2.start();
    }
}

class Person2 {
    public synchronized static void method() {
        for (int i = 0; i < 10; i++) {
            System.out.println("method " + i);
        }
    }

    public synchronized static void method1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("method1 " + i);
        }
    }
}

class MyThread3 implements Runnable {

    private Person2 person;

    public MyThread3(Person2 person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.method();
    }
}

class MyThread4 implements Runnable {

    private Person2 person;

    public MyThread4(Person2 person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.method1();
    }
}
