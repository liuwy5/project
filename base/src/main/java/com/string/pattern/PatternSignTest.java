package com.string.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternSignTest {
    public static void main(String[] args) {
        String s = "aaaa bbbbb\ncccc ddddd";
        String pattern = "^\\w+.*";
        Matcher matcher = Pattern.compile(pattern).matcher(s);
        System.out.println("pattern: " + pattern);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        matcher = Pattern.compile(pattern, Pattern.MULTILINE).matcher(s);
        System.out.println("pattern: " + pattern + ", Pattern.MULTILINE");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        pattern = "(?m)^\\w+.*";
        matcher = Pattern.compile(pattern).matcher(s);
        System.out.println("pattern: " + pattern);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
