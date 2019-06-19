package com.string.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class SimpleRead {
    public static BufferedReader input = new BufferedReader(new StringReader("name\n22 1.5"));

    public static void main(String[] args) {
        System.out.println("your name:");
        try {
            String name = input.readLine();
            System.out.println(name);
            System.out.println("your age & double :");
            String data = input.readLine();
            String[] data1 = data.split(" ");
            int age = Integer.parseInt(data1[0]);
            double doubl = Double.parseDouble(data1[1]);
            System.out.format("age: %d \n", age);
            System.out.format("double: %f", doubl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
