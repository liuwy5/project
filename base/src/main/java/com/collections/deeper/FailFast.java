package com.collections.deeper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * fail-fast产生的原因就在于程序在对 collection 进行迭代时，某个线程对该 collection 在结构上对其做了修改，
 * 这时迭代器就会抛出 ConcurrentModificationException 异常信息，从而产生 fail-fast。
 * 迭代器在调用next()、remove()方法时都是调用checkForComodification()方法，该方法主要就是检测modCount == expectedModCount ?
 * 若不等则抛出ConcurrentModificationException 异常，从而产生fail-fast机制。
 * 所以要弄清楚为什么会产生fail-fast机制我们就必须要用弄明白为什么modCount != expectedModCount ，他们的值在什么时候发生改变的
 * ArrayList中无论add、remove、clear方法只要是涉及了改变ArrayList元素的个数的方法都会导致modCount的改变。
 * 所以我们这里可以初步判断由于expectedModCount 得值与modCount的改变不同步，导致两者之间不等从而产生fail-fast机制
 */
public class FailFast {
    public static List<Integer> list = new ArrayList<>(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

//        new Thread(new Thread1()).start();
        new Thread(new Thread11()).start();
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println("thread1: " + iterator.next());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Thread11 implements Runnable {
        @Override
        public void run() {
            for (Integer item : list) {
                System.out.println("thread11: " + item);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (i < list.size()) {
                System.out.println("thread2: " + list.get(i));

                if (i == 2) {
                    list.remove(i);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;
            }
        }
    }
}


