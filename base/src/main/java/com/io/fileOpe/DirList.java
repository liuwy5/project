package com.io.fileOpe;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    /**
     * + 匹配一次或更多次的重复
     +?匹配一次或更多次的重复，但是在能使整个匹配成功的前提下使用最少的重复
     自行查阅贪婪匹配与懒惰匹配资料
     * @param args
     */
    public static void main(String[] args) {
        String pattern = null;
        File file = new File(".");
        String[] list;
        if (pattern == null) {
            list = file.list();
        } else {
            list = file.list(new DirFilter(pattern));
        }

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (String s : list) {
            System.out.println(s);
        }
    }
}

class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return this.pattern.matcher(name).matches();
    }
}
