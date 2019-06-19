package com.string.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组 matches lookingAt
 */
public class GroupTest {
    public static void main(String[] args) {

        String string = "fds23fds 32im32jk";
        String pattern = "[a-zA-Z]+(\\d+([a-zA-Z]+))\\s*";
        Matcher matcher = Pattern.compile(pattern).matcher(string);
        while (matcher.find()) {
            System.out.println("匹配：" + matcher.group() + ", start: " + matcher.start() + ", end: " + matcher.end());
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("第" + i + "组：" + matcher.group(i));
            }
        }

        System.out.println("String: " + string);
        matcher = Pattern.compile(pattern).matcher(string);
        System.out.println("pattern: " + pattern + ", matches: " + matcher.matches() + ", lookingAt: " + matcher.lookingAt());
        pattern = "\\w+\\s+\\w+";
        matcher = Pattern.compile(pattern).matcher(string);
        System.out.println("pattern: " + pattern + ", matches: " + matcher.matches() + ", lookingAt: " + matcher.lookingAt());

    }
}
