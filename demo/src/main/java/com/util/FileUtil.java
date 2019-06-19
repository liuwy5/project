package com.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtil {
    public static String storeFile(byte[] bytes, String targetDir, String originalFilename) {
        String filename = getFilename(originalFilename);
        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Path path = Paths.get(targetDir + filename);
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public static String getFilename(String originalFilename) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String suffix = originalFilename.contains(".") ?
                originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        return new StringBuilder(sdf.format(new Date())).append("_").append(uuid).append(suffix).toString();
    }
}
