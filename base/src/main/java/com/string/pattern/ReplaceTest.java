package com.string.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTest {
    public static void main(String[] args) {
        String string = "Here's a block of text to use";
        String pattern = "[aeiou]";
        Matcher matcher = Pattern.compile(pattern).matcher(string);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, matcher.group().toUpperCase());
        }
        matcher.appendTail(buffer);
        System.out.println(buffer);

        pattern = "\\s+([aeiou])";
        matcher = Pattern.compile(pattern).matcher(string);
        buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(buffer);
        System.out.println(buffer);

        string = "<a href=\"http://www.baidu.com\" id=\"id\">百度一下，</a><p>你就知道</p><a href=\"http://taobao.com\" id=\"id\">淘宝</a>";
        System.out.println("源字符串：" + string);
        pattern = "<a.*?>([^\\x00-\\xff]*)</a>"; // *?表示匹配满足模式所需的最少字符数
        matcher = Pattern.compile(pattern).matcher(string);
        buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(buffer);
        System.out.println(buffer);

    }
}
