package com.string.scanner;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class ScannerTest {
    public static void main(String[] args) {
        System.out.println("---------------定界符-----------------");
        Scanner scanner = new Scanner("a, b, c, d");
        System.out.println("delimiter: " + scanner.delimiter());
        scanner.useDelimiter("\\s*,\\s*");
        System.out.println("delimiter: " + scanner.delimiter());
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        scanner = new Scanner("a, b, \nc, d");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }

        System.out.println("---------------正则表达式-----------------");
        String string = "132.32.23.23@02/13/1992\n" +
                "132.32.23.24@02/13/1993\n" +
                "132.32.23.25@02/13/1994\n" +
                "132.32.23.26@02/13/1995\n" +
                "fsdfdsfdsfdfdsff";
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        scanner = new Scanner(string);
        while (scanner.hasNext(pattern)) {
            scanner.next(pattern);
            MatchResult matchResult = scanner.match();
            String ip = matchResult.group(1);
            String date = matchResult.group(2);
            System.out.println("ip: " + ip + " on " + date);
        }
    }
}
