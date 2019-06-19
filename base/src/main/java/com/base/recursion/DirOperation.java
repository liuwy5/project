package com.base.recursion;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class DirOperation {
    /**
     * 递归删除目录
     * @param dir 目录
     */
    public static void delDir(File dir) {
        if (dir.isFile()) {
            dir.delete();
//            System.out.println(dir.getName());
        } else {
            File[] listFiles = dir.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
//                    System.out.println(file.getName());
                    delDir(file);
                }
            }
            dir.delete();
        }
    }

    /**
     * 递归显示文件列表
     */
    public static void printDir(File dir, int i) {
//        int i = 0; // 层数
        System.out.println(conStrBuilder(i).append(dir.getName()));
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            if (listFiles != null) {
                i++;

                // 排序
                sort(listFiles);

                for (File file : listFiles) {
                    printDir(file, i);
                }
            }
        }
    }

    static int count = 0;
    /**
     * 递归显示文件列表
     */
    public static void printDir(File dir) {
        System.out.println(conStrBuilder(count).append(dir.getName()));

        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            if (listFiles != null && listFiles.length > 0) {

                // 排序
                sort(listFiles);

                count++;
                for (File file : listFiles) {
                    printDir(file);
                }
                count--;
            }

        }

    }

    public static StringBuilder conStrBuilder(int n) {
        StringBuilder stringBuilder;
        if (n > 0) {
            stringBuilder = new StringBuilder(n * 3);
            for (int i = 0; i < n; i++) {
                stringBuilder.append("   ");
            }
        } else {
            stringBuilder = new StringBuilder();
        }

        return stringBuilder;
    }

    public static void sort(File[] listFiles) {
        // 排序
        Arrays.sort(listFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                } else if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        });
    }

    public static void main(String[] args) {
//        delDir(new File("E:/data/dir/del/"));
//        printDir(new File("E:/data/dir/list/"), 0);
        printDir(new File("E:/data/dir/list/"));
    }
}
