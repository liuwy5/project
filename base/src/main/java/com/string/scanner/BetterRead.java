package com.string.scanner;

import java.util.Scanner;

public class BetterRead {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(SimpleRead.input);

        String name = scanner.nextLine();
        System.out.println("name: " + name);

        Integer age = scanner.nextInt();
        double num = scanner.nextDouble();
        System.out.println("age: " + age + ", num: " + num);
    }
}
