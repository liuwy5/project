package com.enums.enumerated;

public class Menu {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                System.out.print(course.randomSelect() + ", ");
            }
            System.out.println("");
        }
    }
}
