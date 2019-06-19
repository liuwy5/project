package com.io.nio.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {
    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();
            Pipe.SinkChannel sinkChannel = pipe.sink();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("hello world".getBytes());

            byteBuffer.flip();
            // 写入
            sinkChannel.write(byteBuffer);
            byteBuffer.clear();

            Pipe.SourceChannel sourceChannel = pipe.source();
            sourceChannel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));

            sourceChannel.close();
            sinkChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
