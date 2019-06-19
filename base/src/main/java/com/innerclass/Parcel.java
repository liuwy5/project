package com.innerclass;

import sun.awt.SunHints;

public class Parcel {
    public Wrapping wrapping(int value) {
        // 基类需要带参数的构造器
        return new Wrapping(value) {
//            value = 32;
            public int value() {
                return super.value() * 10;
            }
        };
    }

    // 在匿名类中使用一个在其外部定义的对象，那么该参数引用应是final的或者实际上的最终变量
    public Destination destination(String desc) {
        return new Destination() {
            {
                System.out.println("destination constructor");
            }
//            desc = "fsdf";
            private String label = desc;
            String s = desc;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        Wrapping wrapping = parcel.wrapping(100);
        System.out.println("wrapping value: " + wrapping.value());

        Destination destination = parcel.destination("label1");
        String label = destination.readLabel();
        System.out.println("label: " + label);
    }
}
