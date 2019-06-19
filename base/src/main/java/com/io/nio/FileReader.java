package com.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileReader {
    public static void main(String[] args) {
        String fileName = "file/byteArray.txt";
        readFile(fileName, "rw");

        bufferEquals(fileName, "rw");
    }


    public static void readFile(String fileName, String mode) {
        try {
            RandomAccessFile accessFile = new RandomAccessFile(fileName, mode);
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            int byteRead = fileChannel.read(byteBuffer);

            while (byteRead != -1) {
                System.out.println("read " + byteRead);

                // 首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据
                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }

                System.out.println("----------------");
                byteBuffer.rewind(); // 重读数据
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }

                byteBuffer.clear();

                byteRead = fileChannel.read(byteBuffer);
            }

            accessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bufferEquals(String fileName, String mode) {
        try {
            RandomAccessFile accessFile = new RandomAccessFile(fileName, mode);
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            ByteBuffer byteBuffer1 = ByteBuffer.allocate(128);
            int byteRead = fileChannel.read(byteBuffer);

            // 从文件写入byteBuffer
            while (byteRead != -1) {
                System.out.println("read " + byteRead);

                byteRead = fileChannel.read(byteBuffer);
            }

            byte[] bytes = "hello world".getBytes();
            byteBuffer1.put(bytes);

            System.out.println(byteBuffer.equals(byteBuffer1));

            accessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
