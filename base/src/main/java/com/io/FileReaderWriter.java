package com.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderWriter {
    public static void fileReaderWriter() throws Exception {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("file/fileReader"));
        bufferedWriter.write("hello");
        bufferedWriter.write("\nworld");
        bufferedWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("file/fileReader"));

        String string;
        while (null != (string = bufferedReader.readLine())) {
            System.out.println(string);
        }

        bufferedReader.close();
    }

    public static void fileReader() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("base/src/main/java/com/io/FileReaderWriter.java"));

        String string;
        while (null != (string = bufferedReader.readLine())) {
            System.out.println(string);
        }

        bufferedReader.close();
    }

    public static void main(String[] args) throws Exception {
        fileReader();
    }
}
