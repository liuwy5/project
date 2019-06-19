package com.io.fileOpe;

import java.io.File;
import java.nio.file.Files;

public class FileUtil {
    private static boolean rename(String source, String target) {
        try {
            return new File(source).renameTo(new File(target));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void copy(String source, String target) {
        try {
            Files.copy(new File(source).toPath(), new File(target).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String file = "/Users/lw/data/test/file";
        rename("/Users/lw/data/test/2019-08", "/Users/lw/data/test/bak/2019-08");
    }
}
