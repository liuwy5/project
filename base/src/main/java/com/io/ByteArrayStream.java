package com.io;

import java.io.*;

public class ByteArrayStream {
    public static void read() {
        String value = "hello world";
        byte[] bytes = value.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        int i;
        while ((i = inputStream.read()) != -1) {
            System.out.print((char) i);
        }
    }

    public static void write() {
        String value = "hello world";
        byte[] bytes = value.getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytes, 0, bytes.length);

        byte[] bytes1 = outputStream.toByteArray();
        for (byte b : bytes1) {
            System.out.print((char) b);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file/byteArray.txt");
            outputStream.writeTo(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        read();
        System.out.println();
        write();
    }
}
