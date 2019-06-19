package com.innerclass;

public class LocalInnerClassTest {
    private int i = 10;

    public void setI(int i) {
        this.i = i;
    }

    public void method(int m) {
        int s = 3;
        final int z = 5;

        class Mem {
            public void print() {
                System.out.println("i: " + i);
                System.out.println(s);
                System.out.println(z);
                System.out.println(m);
            }
        }

//        m = 9; // 局部内部类中只能访问局部final变量或实际上的最终变量
//        s = 19;
        i = 30;

        new Mem().print();
    }

    public static void main(String[] args) {
        LocalInnerClassTest innerClassTest = new LocalInnerClassTest();

        innerClassTest.setI(25);
        innerClassTest.method(29);
    }
}
