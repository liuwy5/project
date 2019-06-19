package com.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("file/randomAccessFile.txt", "rw");

        randomAccessFile.writeInt(0);
        randomAccessFile.writeUTF("fsdf");
        randomAccessFile.writeUTF("hello");
        randomAccessFile.writeDouble(43.2);

        // 让读的位置重回到文件头
        randomAccessFile.seek(0);

        int i = randomAccessFile.readInt();
        String s = randomAccessFile.readUTF();
        String s1 = randomAccessFile.readUTF();
        double d = randomAccessFile.readDouble();
        System.out.println(i);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(d);
    }
}
