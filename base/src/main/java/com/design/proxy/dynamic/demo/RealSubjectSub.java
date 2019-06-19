package com.design.proxy.dynamic.demo;

public class RealSubjectSub implements SubjectSub {
    @Override
    public void sub(String s) {
        System.out.println("the real subject sub");
        System.out.println("result is : " + new StringBuilder(s).deleteCharAt(0).toString());
    }
}
