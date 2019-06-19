package com.io.nio;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
//        bufferTest();
        directBufferTest();
    }

    public static void bufferTest() {
        String content = "abcde";

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        printBuffer(buffer, "allocate");

        // put
        buffer.put(content.getBytes());
        printBuffer(buffer, "put");

        // 转为读状态
        buffer.flip();
        byte[] bytes = new byte[content.length()];
        buffer.get(bytes);
        printBuffer(buffer, "get");
        System.out.println("content: " + new String(bytes));

        buffer.rewind();
        printBuffer(buffer, "rewind");
        // 并不会导致position移动
        byte b = buffer.get(0);
        byte b1 = buffer.get(1);
        System.out.println("get two: " + (char) b + (char) b1 + ", remaining: " + buffer.remaining());
        printBuffer(buffer, "get two");

        buffer.get(bytes, 0, 2);
        System.out.println("content: " + new String(bytes, 0, 2));
        printBuffer(buffer, "get bytes 2");

        // mark
        buffer.mark();

        // remaining
        if (buffer.hasRemaining()) {
            int remain = buffer.remaining();
            buffer.get(bytes, 0, remain);
            System.out.println("get: " + new String(bytes, 0, remain));
        }
        printBuffer(buffer, "mark get");

        // reset
        buffer.reset();
        printBuffer(buffer, "reset");

        // clear之后并没有清空buffer
        buffer.clear();
        printBuffer(buffer, "clear");
        byte b2 = buffer.get(0);
        System.out.println("clear get: " + (char) b2);
    }

    public static void directBufferTest() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println("buffer.isDirect: " + buffer.isDirect());

        buffer = ByteBuffer.allocate(1024);
        System.out.println("buffer.isDirect: " + buffer.isDirect());
    }

    private static void printBuffer(ByteBuffer buffer, String operation) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(operation).append(" >>> position: ").append(buffer.position()).append(", limit: ").append(buffer.limit()).
//                append(", capacity: ").append(buffer.capacity());
//        System.out.println(builder.toString());
        System.out.println(operation + " >>> position: " + buffer.position() + ", limit: " + buffer.limit() +
                ", capacity: " + buffer.capacity());
    }
}
