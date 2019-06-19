package com.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NonBlockingClientSocket {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9899));

            // 设置为非阻塞模式
            socketChannel.configureBlocking(false);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("连接服务端".getBytes());

            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            System.out.println("客户端发送数据");

            socketChannel.shutdownOutput();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
