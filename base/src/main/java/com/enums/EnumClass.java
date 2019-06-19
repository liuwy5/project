package com.enums;

public class EnumClass {
    enum Shrubbery {GROUND, CRAWLING, HANGING}

    public static void main(String[] args) {
        for (Shrubbery shrubbery : Shrubbery.values()) {
            System.out.println("declaring class: " + shrubbery.getDeclaringClass());
        }

        // 向上转型 通过getEnumConstants()方法获取values()
        System.out.println("------------------通过getEnumConstants()方法获取values()------------------------");
        Shrubbery[] shrubberies = Shrubbery.values();
        System.out.print("enum values: ");
        for (Enum s : shrubberies) {
            System.out.print(s + ", ");
        }
        System.out.print("\ngetEnumConstants()获取：");

        for (Enum s : Shrubbery.class.getEnumConstants()) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }
}
