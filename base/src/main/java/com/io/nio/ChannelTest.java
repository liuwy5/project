package com.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class ChannelTest {
    public static void main(String[] args) {
//        copyFile();
//        copyFileDirect();
//        channelTransfer();
//        scatGather();
        charset();
    }

    /**
     * FileInputStream/FileOutputStream.getChannel()获得缓冲区
     */
    public static void copyFile() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fileInputStream = new FileInputStream("file/source");
            fileOutputStream = new FileOutputStream("file/target");
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inChannel.read(byteBuffer) != -1) {
                // 缓冲区切换读模式
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 直接缓冲区复制文件
     */
    public static void copyFileDirect() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("file", "source"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("file", "target_1"),
                    StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            // 内存映射文件
            MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            byte[] bytes = new byte[inBuf.limit()];

            inBuf.get(bytes);
            outBuf.put(bytes);

            inChannel.close();
            outChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通道之间数据传输
     */
    public static void channelTransfer() {
        RandomAccessFile inFile;
        RandomAccessFile outFile;
        FileChannel inChannel;
        FileChannel outChannel;
        try {
            inFile = new RandomAccessFile("file/source", "rw");
            outFile = new RandomAccessFile("file/target_1", "rw");
            inChannel = inFile.getChannel();
            outChannel = outFile.getChannel();
//            inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());

            // 关闭
            inChannel.close();
            outChannel.close();
            inFile.close();
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分散读取 聚集写入
     */
    public static void scatGather() {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("file", "source"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("file", "target_2"),
                    StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            ByteBuffer buf1 = ByteBuffer.allocate(10);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);

            ByteBuffer[] buffers = {buf1, buf2};
            inChannel.read(buffers);

            for (ByteBuffer byteBuffer : buffers) {
                byteBuffer.flip();

                // 打印byteBuffer
                System.out.println("array print: " + new String(byteBuffer.array(), 0, byteBuffer.limit()));

                // 打印byteBuffer
                // 已经读取 如要写入 需要rewind
//                byte[] bytes = new byte[byteBuffer.limit()];
//                byteBuffer.get(bytes);
//                System.out.println(new String(bytes));
//                byteBuffer.rewind();
//                outChannel.write(byteBuffer);
            }

            outChannel.write(buffers);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void charset() {
        // 字符编码
        SortedMap<String, Charset> charsetMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = charsetMap.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getKey());
        }

        Charset charset = Charset.forName("utf-8");
        // 获取编码器
        CharsetEncoder encoder = charset.newEncoder();
        // 获取解码器
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("宁静致远");

        try {
            charBuffer.flip();
            // 编码
            ByteBuffer byteBuffer = encoder.encode(charBuffer);
//            byte[] bytes = byteBuffer.array();
//            for (byte b : bytes) {
//                System.out.print(b + " ");
//            }

            for (int i = 0; i < 10; i++) {
                System.out.print(byteBuffer.get(i) + " ");
            }
            System.out.println();

            // 不需要flip
//            byteBuffer.flip();
            // 解码
            CharBuffer result = decoder.decode(byteBuffer);
            System.out.println(result.toString());

        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }
}
