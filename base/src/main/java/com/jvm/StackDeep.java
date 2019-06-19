package com.jvm;

public class StackDeep {
    private static int i = 0;

    public void call() {
        i++;
        call();
    }

    public static void main(String[] args) {
        StackDeep stackDeep = new StackDeep();
        try {
            stackDeep.call();
        } catch (Exception e) {
            System.out.println("index: " + i);
            e.printStackTrace();
        }
    }
}
