package com.string.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest1 {
    public static void main(String[] args) {
        System.out.println("单词边界\\b&非单词边界\\B");
        String str="(中文问号12？123???英文)问号?我是华丽[的制表符\t]我是华丽{的空格符 我是华丽}的换行符\n";
        String rex="\\b";
        System.out.println("\\b测试：");
        Pattern pattern=Pattern.compile(rex);
        String [] result=pattern.split(str);
        for(String string:result){
            System.out.println("分割的字符串:"+"["+string+"]");
        }

        System.out.println("\\B测试：");
        str="123456我是JAVA{，、；‘asd";
        rex="\\B";
        pattern=Pattern.compile(rex);
        result=pattern.split(str);
        for(String string:result){
            System.out.println("分割的字符串:"+string);
        }

        System.out.println("---------------------------------------");
        String string1 = "Java now has regular expressions";
        System.out.println("string: " + string1);
        String[] patterns = {"^Java", "\\b\\sreg", "\\breg", "s{4}"};
        for (String pattern1 : patterns) {
            System.out.println("pattern: " + pattern1);
            Pattern pattern2 = Pattern.compile(pattern1);
            Matcher matcher = pattern2.matcher(string1);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }

    }
}
