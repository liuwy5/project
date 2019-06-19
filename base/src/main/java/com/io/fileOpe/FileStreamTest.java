package com.io.fileOpe;

import java.io.*;

public class FileStreamTest {
    public static void inputStreamTest(File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[300];
            int length;
            while (-1 != (length = inputStream.read(bytes, 0, 300))) {
                String string = new String(bytes);
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void outputStreamTest() {
        File file = new File("E:\\data\\dir\\file\\b.txt");
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            outputStream.write("fsdfsfsdfasfsd".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("E:\\data\\dir\\file\\a.txt");
        inputStreamTest(file);
        outputStreamTest();
    }
}
