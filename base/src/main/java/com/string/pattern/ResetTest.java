package com.string.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetTest {
    public static void main(String[] args) {
        System.out.println("------------正常匹配-------------");
        String s = "abcabc";
        Matcher matcher = Pattern.compile("abc").matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("------------再次匹配-------------");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("------------reset之后匹配-------------");
        matcher.reset();
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("------------新字符串匹配-------------");
        String s1 = "abcabcabc";
        matcher.reset(s1);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
