package com.concurrency.share.synchronize;

public class SynchronizedTest {
    public static void main(String[] args) {
        synchronizedTest();
    }

    /**
     * 对象的synchronized方法与synchronized(this)都是对对象加锁
     * 对象的static synchronized方法与synchronized(ClassName.clss)都是对类加锁
     */
    public static void synchronizedTest() {
        SynchronizedUnit unit = new SynchronizedUnit();
        new Thread() {
            @Override
            public void run() {
                unit.method();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                unit.methodBlock();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                SynchronizedUnit.staticMethod();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                SynchronizedUnit.staticMethodBlock();
            }
        }.start();
    }
}

class SynchronizedUnit {
    public synchronized void method() {
        methodBody("method");
    }

    public void methodBlock() {
        synchronized (this) {
            methodBody("methodBlock");
        }
    }

    public static synchronized void staticMethod() {
        methodBody("staticMethod");
    }

    public static void staticMethodBlock() {
        synchronized (SynchronizedUnit.class) {
            methodBody("staticMethodBlock");
        }
    }

    private static void methodBody(String name) {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}