package com.io;

import java.io.*;

public class DataStream {
    public static void read() throws IOException{
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(
                new FileInputStream("file/dataStream.txt")));
        int i = inputStream.readInt();
        boolean b = inputStream.readBoolean();
        char c = inputStream.readChar();
        inputStream.close();

        System.out.println(i);
        System.out.println(b);
        System.out.println(c);
    }

    public static void write() throws IOException{
        DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("file/dataStream.txt")));
        outputStream.writeInt(0);
        outputStream.writeBoolean(true);
        outputStream.writeChar('a');
        outputStream.close();
    }

    public static void main(String[] args) throws Exception {
        write();
        read();
    }
}
