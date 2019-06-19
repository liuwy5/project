package com.string.base;

public class ToStringAttention {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person);
    }
}

class Person {
    @Override
    public String toString() {
        // 会发生递归调用报错
//        return this.toString();

//        return "Person2: " + this;
        return super.toString();
    }
}
