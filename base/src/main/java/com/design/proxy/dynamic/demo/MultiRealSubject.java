package com.design.proxy.dynamic.demo;

public class MultiRealSubject implements Subject, SubjectSub {
    @Override
    public void reverse(String s) {
        System.out.println("the MultiRealSubject");
        System.out.println("result is : " + new StringBuilder(s).reverse().toString());
    }

    @Override
    public void sub(String s) {
        System.out.println("the MultiRealSubject sub");
        System.out.println("result is : " + new StringBuilder(s).deleteCharAt(0).toString());
    }
}
