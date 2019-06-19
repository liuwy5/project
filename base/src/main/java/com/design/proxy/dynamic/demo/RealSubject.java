package com.design.proxy.dynamic.demo;

public class RealSubject implements Subject {
    @Override
    public void reverse(String s) {
        System.out.println("the real subject");
        System.out.println("result is : " + new StringBuilder(s).reverse().toString());
    }
}
