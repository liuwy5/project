package com.io;

import java.io.*;

public class StreamReaderWriter {
    public static void main(String[] args) throws Exception {
        streamTest2();
    }

    public static void streamTest1() throws Exception {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("file/streamReaderWriter"));
        BufferedWriter bufferedWriter = new BufferedWriter(osw);

        bufferedWriter.write("http://www.baidu.com");
        bufferedWriter.write("\n");
        bufferedWriter.write("http://aaa.com");

        bufferedWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream("file/streamReaderWriter")));

        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());

        bufferedReader.close();
    }

    public static void streamTest2() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String string;
        while (null != (string = bufferedReader.readLine())) {
            System.out.println(string);
        }

        bufferedReader.close();
    }
}
